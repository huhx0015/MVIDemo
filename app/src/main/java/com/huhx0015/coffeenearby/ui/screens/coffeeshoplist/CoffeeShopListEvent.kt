package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import com.huhx0015.coffeenearby.ui.base.BaseEvent

sealed class CoffeeShopListEvent : BaseEvent {
  data class ErrorEvent(val errorMessage: String?) : CoffeeShopListEvent()
}