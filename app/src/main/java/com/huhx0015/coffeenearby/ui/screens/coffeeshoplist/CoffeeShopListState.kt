package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import android.os.Parcelable
import com.huhx0015.coffeenearby.ui.base.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeShopListState(
  val isLoading: Boolean = false
) : BaseState, Parcelable {
}