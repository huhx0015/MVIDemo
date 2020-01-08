package com.envoy.mvidemo.ui.main

sealed class MainState {
  object BaseState : MainState()
  object LoadingState : MainState()
  object ReadyState : MainState()
  data class ClickState(val someData: String) : MainState()
}
