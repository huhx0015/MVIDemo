package com.huhx0015.coffeenearby.di.modules

import com.huhx0015.coffeenearby.data.repositories.CoffeeShopRepository
import com.huhx0015.coffeenearby.network.CoffeeShopApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun provideCoffeeShopRepository(
    coffeeShopApi: CoffeeShopApi
  ): CoffeeShopRepository {
    return CoffeeShopRepository(coffeeShopApi = coffeeShopApi)
  }
}
