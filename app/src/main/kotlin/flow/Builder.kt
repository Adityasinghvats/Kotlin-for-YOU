package org.example.app.flow

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//flow builder Lesson 1
fun producer() = flow<Int>{
    val list = listOf(1,2,3,4,5,6,7,8,9,23,56)
    list.forEach{
        delay(1000)//mimic db response delay
        emit(it)
    }
}

//there is no specific cancel function but it cancels when no consumer
//we can mimic that using the structured concurrency by cancelling a coroutine instead

suspend fun data() = coroutineScope {
    val data:Flow<Int> = producer()
    data.collect{
        println(it.toString())
    }
}

suspend fun flowData() = coroutineScope{
    val job = async { data() }
    delay(3500)
    job.cancel()
}
//flow cancel out only after printing up to 3

suspend fun main(){
    flowData()
}