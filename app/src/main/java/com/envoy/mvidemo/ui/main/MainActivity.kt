package com.envoy.mvidemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.envoy.mvidemo.R

class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels()
  
  private lateinit var button1: Button
  private lateinit var button2: Button
  private lateinit var loadButton: Button
  private lateinit var progressBar: ProgressBar
  private lateinit var stateTextView: TextView
  private lateinit var textView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initView()
    observe()
  }

  private fun initView() {
    setContentView(R.layout.main_activity)
    
    button1 = findViewById(R.id.button1)
    button2 = findViewById(R.id.button2)
    loadButton = findViewById(R.id.loadButton)
    progressBar = findViewById(R.id.progressBar)
    stateTextView = findViewById(R.id.stateTextView)
    textView = findViewById(R.id.textView)
    
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
