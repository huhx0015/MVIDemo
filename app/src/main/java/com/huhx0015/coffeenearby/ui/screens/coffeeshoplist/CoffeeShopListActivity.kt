package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.huhx0015.coffeenearby.ui.screens.coffeeshoplist.compose.CoffeeShopScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeShopListActivity : ComponentActivity() {
  private val viewModel: CoffeeShopListViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    renderCompose()

    viewModel.sendIntent(CoffeeShopListIntent.LoadCoffeeShopListIntent)
  }

  private fun renderCompose() {
    setContent {
      val state by viewModel.state.collectAsState()
      CoffeeShopScreen(state = state)
    }
  }
}