package com.example.phonepe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.phonepe.model.Logo
import com.example.phonepe.repository.Repo

class MainViewModel(private val repo: Repo): ViewModel() {

    private val trigger = MutableLiveData<Boolean>()

    private val logoIndex = MutableLiveData<Int>()

    private var logoList = ArrayList<Logo>()

    fun getLogos() {
        if(trigger.value != true)
            trigger.value = true
    }

    fun addLogos(data: List<Logo>?) {
        if (logoList.size == 0) {
            logoList.addAll(data!!)
            logoIndex.value = 0
        }
    }

    var observableLogoList = Transformations.switchMap(trigger) {
        repo.getData()
    }

    var observableLogo = Transformations.map(logoIndex){
        logoList[it]
    }

    val observableScore = MutableLiveData(0)

    fun submitAnswer(s: String){
        if(isCorrectAnswer(s)){
            observableScore.value = observableScore.value!!+1
        }
        if(logoIndex.value!! < logoList.size)
            logoIndex.value = logoIndex.value!! + 1
    }

    private fun isCorrectAnswer(s: String) = s.equals(logoList[logoIndex.value!!].name,true)

}