package com.example.falcone.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class DeleteVM: ViewModel() {

    val scope = CoroutineScope(SupervisorJob()+Dispatchers.IO).plus(viewModelScope.coroutineContext)
    fun run(){
        viewModelScope.launch(Dispatchers.IO) {
            var i = 0L
            while (true) {
                    Log.d("ishan",(i++).toString())
            }
            Log.d("ishan","exit")
        }
    }
lateinit var context: Context
    override fun onCleared() {
        super.onCleared()
        Log.d("poopoo","clear")
    }
}


val countFlow = flow{
    var count =10
    while(count>=0){
        emit(count--)
        delay(200)
    }
}
fun main(){
    runBlocking {
        launch {
            countFlow.collect {
                println(it)
            }
        }
        val a = IntArray(4)

        println("done")
        delay(1000)
        countFlow.collect{
            println(it)
        }
        println("done")
    }
}