package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import androidx.lifecycle.viewModelScope
import com.huhx0015.coffeenearby.data.repositories.CoffeeShopRepository
import com.huhx0015.coffeenearby.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(
  val coffeeShopRepository: CoffeeShopRepository
) : BaseViewModel<CoffeeShopListState, CoffeeShopListIntent, CoffeeShopListEvent>() {

  private val _state = MutableStateFlow(CoffeeShopListState())
  override val state: StateFlow<CoffeeShopListState> = _state.asStateFlow()

  private val _events = MutableSharedFlow<CoffeeShopListEvent>(
    replay = 1,
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
  )
  override val events = _events.asSharedFlow()

  private val _searchQueryFlow = MutableStateFlow(String())
  private val searchQueryFlow: StateFlow<String> = _searchQueryFlow.asStateFlow()

  init {
    observeSearchQueryFlow()
  }

  override suspend fun processIntent(intent: CoffeeShopListIntent) {
    when (intent) {
      is CoffeeShopListIntent.LoadCoffeeShopListIntent -> onLoadCoffeeShopList()
      is CoffeeShopListIntent.LoadMoreCoffeeShopsIntent -> onLoadMoreCoffeeShops()
      is CoffeeShopListIntent.RefreshCoffeeShopListIntent -> onRefreshCoffeeShopList()
      is CoffeeShopListIntent.SearchQueryChangedIntent -> onSearchQueryChanged(intent.query)
    }
  }

  companion object {
    private const val VAL_DEFAULT_TERM = "coffee"
    private const val SEARCH_DEBOUNCE_MS = 500L
  }

  private fun observeSearchQueryFlow() {
    viewModelScope.launch {
      searchQueryFlow
        .debounce(SEARCH_DEBOUNCE_MS)
        .distinctUntilChanged()
        .drop(1)
        .collectLatest { query ->
          val term = query.trim().ifBlank { VAL_DEFAULT_TERM }
          _state.update {
            it.copy(currentOffset = 0, hasMorePages = true).coffeeShopLoading()
          }
          loadCoffeeShopsWithTerm(term = term)
        }
    }
  }

  private fun onSearchQueryChanged(query: String) {
    _searchQueryFlow.update { query }
    _state.update { it.copy(searchQuery = query) }
  }

  private fun onLoadCoffeeShopList() {
    val isLoading = state.value.isCoffeeShopListLoading
    if (isLoading) return

    _state.update { it.coffeeShopLoading() }

    viewModelScope.launch {
      loadCoffeeShopsWithTerm(term = VAL_DEFAULT_TERM)
    }
  }

  private suspend fun loadCoffeeShopsWithTerm(term: String) {
    runCatching {
      coffeeShopRepository.getCoffeeShops(term = term)
    }.onSuccess { coffeeShopList ->
      _state.update { it.coffeeShopLoaded(coffeeShopList) }
    }.onFailure { error ->
      _events.emit(CoffeeShopListEvent.ErrorEvent(errorMessage = error.message))
      _state.update { it.copy(isCoffeeShopListLoading = false, isCoffeeShopListRefreshing = false) }
    }
  }

  private fun onRefreshCoffeeShopList() {
    val isRefreshing = state.value.isCoffeeShopListRefreshing
    if (isRefreshing) return

    _state.update { it.copy(currentOffset = 0, hasMorePages = true).coffeeShopRefreshing() }
    val term = state.value.searchQuery.trim().ifBlank { VAL_DEFAULT_TERM }
    viewModelScope.launch {
      loadCoffeeShopsWithTerm(term = term)
    }
  }

  private fun onLoadMoreCoffeeShops() {
    val currentState = state.value
    val isLoadingMore = currentState.isCoffeeShopListLoadingMore
    val isLoading = currentState.isCoffeeShopListLoading
    val hasMorePages = currentState.hasMorePages

    if (isLoadingMore || isLoading || !hasMorePages) return

    _state.update { it.coffeeShopLoadingMore() }
    loadMoreCoffeeShops()
  }

  private fun loadMoreCoffeeShops() {
    viewModelScope.launch {
      val term = state.value.searchQuery.trim().ifBlank { VAL_DEFAULT_TERM }
      runCatching {
        coffeeShopRepository.getCoffeeShops(
          term = term,
          offset = state.value.currentOffset
        )
      }.onSuccess { newCoffeeShops ->
        _state.update { it.coffeeShopLoadedMore(newCoffeeShops) }
      }.onFailure { error ->
        _events.emit(CoffeeShopListEvent.ErrorEvent(errorMessage = error.message))
        _state.update { it.copy(isCoffeeShopListLoadingMore = false) }
      }
    }
  }
}