package org.example.app.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//By default the producer and consumer runs on main thread and in same coroutine context ,
// but we dont want that to happen so we use withContext to make sure data inflow is not
//using main thread that is to be used by UI, but we face error as flow preserve their context
//meaning they want input on same thread as the output
// To switch context -> both on different thread we use FLOWON

fun producer2() = flow<Int>{
        val list = listOf(1,2,3,4,5,6,7,8,9,23,56)
        list.forEach{
            delay(1000)//mimic db response delay
            println("Producer using thread: "+Thread.currentThread().name)
            emit(it)
        }
}

suspend fun main(){
        coroutineScope {
            producer2()
                .map {
                    delay(1000)
                    println("Map using thread: "+Thread.currentThread().name)
                    it*2
                }
                .flowOn(Dispatchers.IO)
                //telling consumer to look for flow on IO thread for-above-code
                //others on the main thread
                .collect{
                    println(it.toString())
                    println("Collect using thread: "+Thread.currentThread().name)
                }
        }
}