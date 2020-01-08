package com.envoy.mvidemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

  internal val state = MutableLiveData<MainState>(MainState.BaseState)

  internal fun onClickLoad() {
    state.value = MainState.LoadingState
    loadSomething()
  }

  internal fun onClickButton1() {
    state.value = MainState.ClickState("YOU CLICKED BUTTON 1")
  }

  internal fun onClickButton2() {
    state.value = MainState.ClickState("YOU CLICKED BUTTON 2")
  }

  private fun loadSomething() = viewModelScope.launch {
    // TODO: Some async method that happens in the background
  }
}
