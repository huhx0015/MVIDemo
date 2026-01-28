package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import com.huhx0015.coffeenearby.ui.base.BaseViewModelV2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(

) : BaseViewModelV2<CoffeeShopListState, CoffeeShopListIntent, CoffeeShopListEvent>() {

  override val state: StateFlow<CoffeeShopListState>
    get() = TODO("Not yet implemented")
  override val events: Flow<CoffeeShopListEvent>
    get() = TODO("Not yet implemented")

  override suspend fun processIntent(intent: CoffeeShopListIntent) {
    TODO("Not yet implemented")
  }

}