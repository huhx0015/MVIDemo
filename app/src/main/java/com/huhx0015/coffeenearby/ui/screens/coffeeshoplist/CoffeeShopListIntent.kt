package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import com.huhx0015.coffeenearby.ui.base.BaseIntent

sealed class CoffeeShopListIntent : BaseIntent {
  data object LoadCoffeeShopListIntent: CoffeeShopListIntent()
  data object LoadMoreCoffeeShopsIntent: CoffeeShopListIntent()
  data object RefreshCoffeeShopListIntent: CoffeeShopListIntent()
}