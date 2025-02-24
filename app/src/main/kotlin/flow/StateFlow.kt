package org.example.app.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(DelicateCoroutinesApi::class)
fun producerForState(): Flow<Int> {
    val mutableStateFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000)
        mutableStateFlow.emit(10)
        delay(2000)
        mutableStateFlow.emit(20)
        delay(2000)
        mutableStateFlow.emit(30)
    }
    return mutableStateFlow
}

//shared flow is a hot flow
@OptIn(DelicateCoroutinesApi::class)
fun main(){
    GlobalScope.launch {
        val result = producerForState()
        delay(6000)
        result.collect{
            println("Consumer 2: $it")
        }
    }
}
/*
State flow is also a shared flow means it is hot
It maintains last value of flow in form of a state, like a buffer
Think of it as a box which stores 1 value and gives it to all consumer
As stateflow maintains a state. It will give consumers the latest value even if nothing is being produced
* */
