package com.huhx0015.coffeenearby.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeShop(
  val id: String,
  val name: String,
  val imageUrl: String?,
  val rating: Double,
  val price: String?,
  val distance: Double,
  val address: String?
) : Parcelable