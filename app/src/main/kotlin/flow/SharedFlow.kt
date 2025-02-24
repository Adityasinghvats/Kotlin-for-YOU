package org.example.app.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


//sharedflow is a hot flow , so delay in receiving result in data loss
//we can use > MutableSharedFlow<Int>(replay=1) , it can buffer 1 data to prevent loss
private suspend fun producerForHere(): Flow<Int> {
    val mutableSharedFlow = MutableSharedFlow<Int>()
    coroutineScope {
        val list = listOf(1,2,3,5,6)
        list.forEach {
            mutableSharedFlow.emit(it)
            delay(1000)
        }
    }
    return mutableSharedFlow
}

//shared flow is a hot flow
suspend fun main(){
    coroutineScope {
        val result = producerForHere()
        result.collect{
            println("Consumer 1: $it")
        }
    }
    coroutineScope {
        val result = producerForHere()
        delay(3500)
        result.collect{
            println("Consumer 2: $it")
        }
    }
}