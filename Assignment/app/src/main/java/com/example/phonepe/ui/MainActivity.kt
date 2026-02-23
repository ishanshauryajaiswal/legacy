package com.example.phonepe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.phonepe.R
import com.example.phonepe.model.Status
import com.example.phonepe.utils.*
import com.example.phonepe.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(MainViewModel::class.java)

        setUpObservers()

        viewModel.getLogos()

        btn_submit.setOnClickListener{
            val s = et_logo_name.text.toString()
            if(isValidInput(s)) {
                viewModel.submitAnswer(s)
                et_logo_name.setText("")
            }
            else
                makeToast("Input too short")
        }
    }

    private fun setUpObservers() {
        viewModel.observableLogoList.observe(this, Observer {
            it?.let {
                when(it.status){
                    Status.ERROR -> {
                        pb_home.invisible()
                    }

                    Status.LOADING -> {
                        pb_home.show()
                    }

                    Status.SUCCESS -> {
                        pb_home.invisible()
                        viewModel.addLogos(it.data)
                    }
                }
            }
        })

        viewModel.observableLogo.observe(this, Observer {
            iv_logo.loadImage(it.imageUrl)
        })

        viewModel.observableScore.observe(this, Observer {
            tv_score.text = "Score: $it"
        })
    }

    private fun isValidInput(s: String) = s.length >=3
}
