package org.example.app.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

fun producer3() :Flow<Int>{
    return  flow<Int>{
        val list = listOf(1,2,3,4,5,6,7,8,9,23,56)
        list.forEach{
            delay(1000)//mimic db response delay
            println("Producer using thread: "+Thread.currentThread().name)
            throw Exception("error in producer")
            emit(it)
        }
    }.catch { //can handle producer exception here only
        println("Emitter catch ${it.message}")

        //fallback for error
        emit(-1)
    }
}

suspend fun main(){
    coroutineScope {
        try {
            producer3()
                .collect{
                    println(it.toString())
                    println("Collect using thread: "+Thread.currentThread().name)
                }
        }catch (e:Exception){
            println(e.message.toString())
        }
    }
}