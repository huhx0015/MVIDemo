package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import android.os.Parcelable
import com.huhx0015.coffeenearby.data.models.CoffeeShop
import com.huhx0015.coffeenearby.ui.base.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeShopListState(
  val coffeeShopList: List<CoffeeShop> = emptyList(),
  val isCoffeeShopListLoading: Boolean = false,
  val isCoffeeShopListLoadingMore: Boolean = false,
  val isCoffeeShopListRefreshing: Boolean = false
) : BaseState, Parcelable {

  fun coffeeShopLoading(): CoffeeShopListState = this.copy(
    isCoffeeShopListLoading = true
  )

  fun coffeeShopLoaded(coffeeShopList: List<CoffeeShop>): CoffeeShopListState = this.copy(
    coffeeShopList = coffeeShopList
  )
}