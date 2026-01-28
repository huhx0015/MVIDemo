package com.huhx0015.coffeenearby.network

import com.huhx0015.coffeenearby.network.models.CoffeeShopResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoffeeShopApi {

  @GET("/v3/businesses/search")
  fun getCoffeeShopBusinesses(
    @Query("location") location: String? = null,
    @Query("latitude") latitude: Double? = null,
    @Query("longitude") longitude: Double? = null,
    @Query("term") term: String? = null,
    @Query("radius") radius: Int? = null,
    @Query("categories") categories: String? = null,
    @Query("locale") locale: String? = null,
    @Query("price") price: String? = null,
    @Query("open_now") openNow: Boolean? = null,
    @Query("open_at") openAt: Int? = null,
    @Query("attributes") attributes: String? = null,
    @Query("sort_by") sortBy: String = "best_match",
    @Query("device_platform") devicePlatform: String? = null,
    @Query("reservation_date") reservationDate: String? = null,
    @Query("reservation_time") reservationTime: String? = null,
    @Query("reservation_covers") reservationCovers: Int? = null,
    @Query("matches_party_size_param") matchesPartySizeParam: Boolean? = null,
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int? = null
  ): Call<CoffeeShopResponse>
}