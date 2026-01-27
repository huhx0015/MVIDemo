package com.huhx0015.coffeenearby.ui.main

sealed class MainState {
  object BaseState : MainState()
  object LoadingState : MainState()
  object ReadyState : MainState()
  data class ClickState(val someData: String) : MainState()
}
