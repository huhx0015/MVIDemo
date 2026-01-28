package com.huhx0015.coffeenearby.ui.screens.coffeeshoplist.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.huhx0015.coffeenearby.R
import com.huhx0015.coffeenearby.ui.screens.coffeeshoplist.CoffeeShopListState

@Composable
internal fun CoffeeShopScreen(
  state: CoffeeShopListState
) {
  Scaffold(
    topBar = {
      CoffeeShopScreenTopBar()
    },
    floatingActionButton = {
      CoffeeShopFloatingActionButton(
        floatingActionButtonClick = {  }
      )
    }
  ) { _ ->
    CoffeeShopScreenContent(
      state = state
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoffeeShopScreenTopBar() {
  TopAppBar(
    title = { Text(
      text = stringResource(R.string.app_name)
    ) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary
    )
  )
}

@Composable
private fun CoffeeShopFloatingActionButton(
  floatingActionButtonClick: () -> Unit
) {
  FloatingActionButton(
    onClick = { floatingActionButtonClick.invoke() },
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary
  ) {
//    Icon(
//      imageVector = Icons.Default.Refresh,
//      contentDescription = stringResource(R.string.floating_button_description)
//    )
  }
}


@Composable
private fun CoffeeShopScreenContent(
  state: CoffeeShopListState
) {

}

@Preview
@Composable
private fun CoffeeShopScreenTopBarPreview() {
  CoffeeShopScreenTopBar()
}