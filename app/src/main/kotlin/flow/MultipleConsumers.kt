package org.example.app.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//using producer from same package Lesson 2
suspend fun consume1() = coroutineScope {
    val data: Flow<Int> = producer()
    data.collect{
        println("Consumer 1: $it")
    }
}
suspend fun consume2() = coroutineScope {
    val data: Flow<Int> = producer()
    delay(2500)
    data.collect{
        println("Consumer 2: $it")
    }
}
fun main(){
    runBlocking {
        launch { consume1() }
        launch { consume2() }
    }
}
/*
* Output is something like this both consumers receive data
Consumer 1: 1
Consumer 2: 1
Consumer 1: 2
Consumer 2: 2
* On adding a delay to consumer 2 , we see that when it starts after w while
* it is receiving the same data, and it receives all the data , nothing is lost.
* every flow is independent , it is benefit of cold streams
* But if it was hot stream , data would be lost for late consumers.
* */