package com.envoy.mvidemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.envoy.mvidemo.R
import kotlinx.android.synthetic.main.main_activity.button1
import kotlinx.android.synthetic.main.main_activity.button2
import kotlinx.android.synthetic.main.main_activity.loadButton
import kotlinx.android.synthetic.main.main_activity.progressBar
import kotlinx.android.synthetic.main.main_activity.stateTextView
import kotlinx.android.synthetic.main.main_activity.textView

class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initView()
    observe()
  }

  private fun initView() {
    setContentView(R.layout.main_activity)
    initButton()
  }

  private fun initButton() {
    button1.setOnClickListener {
      viewModel.onClickButton1()
    }
    button2.setOnClickListener {
      viewModel.onClickButton2()
    }
    loadButton.setOnClickListener {
      viewModel.onClickLoad()
    }
  }

  private fun observe() {
    viewModel.state.observe(this, Observer { state ->

      when (state) {
        is MainState.BaseState -> {
          progressBar.visibility = View.GONE
          stateTextView.text = "Base State"
        }
        is MainState.LoadingState -> {
          textView.visibility = View.GONE
          progressBar.visibility = View.VISIBLE
          stateTextView.text = "Loading State"
        }
        is MainState.ReadyState -> {
          progressBar.visibility = View.GONE
          stateTextView.text = "Ready State"
        }
        is MainState.ClickState -> {
          progressBar.visibility = View.GONE
          textView.visibility = View.VISIBLE
          textView.text = state.someData
          stateTextView.text = "Click State"
        }
      }

    })
  }
}
