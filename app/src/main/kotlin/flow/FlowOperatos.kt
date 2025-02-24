package org.example.app.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import morefeatures.message
import kotlin.system.measureTimeMillis

suspend fun getData() = coroutineScope {
    val data: Flow<Int> = producer()
    data
        .onStart {//we can emit value from this lambda manually as well
            //use case when flow starts and data is still coming, show a loader in the meantime
            emit(69)
            println("Starting out")
        }
        .onCompletion {//we can emit value from this lambda manually as well after completion
            emit(-1)
            println("Completed")
        }
        .onEach {
            println("About to emit: $it")
        }
        .collect{
            println("Consumer: $it")
        }
}
//terminal operators
suspend fun getData2() = coroutineScope {
    val data: Flow<Int> = producer()
    val first = data.first()
    println(first)
    val list = data.toList()
    println(list)
}
//non - terminal operators
suspend fun getData3() = coroutineScope {
    val data: Flow<Int> = producer()
    data
        .map {
            it*2
        }
        .filter{
            it > 6
        }
        .collect{
            println(it)
        }
}

//buffer to mimic delay in consumption we use delay(1500)
suspend fun getData4() = coroutineScope {
    val time = measureTimeMillis {
        val data: Flow<Int> = producer()
        data
            .buffer(3)
            .collect{
            delay(1500)
            println(it)
        }
    }
    println("Time: $time")
    //without buffer 27 sec
    //with buffer 17 sec
}

/*<-----------Flow creations for android usage------------>*/
data class Note(val name:String, val isActive:Boolean)
data class NoteDisplay(val isActive: Boolean, val title:String)
//build a flow
fun getNotes():Flow<Note>{
    val notes = listOf(
        Note("New", true),
        Note("Medium", false),
        Note("High", true)
    )
    return notes.asFlow()
}
suspend fun makeNotes() = coroutineScope {
    getNotes().map {
        NoteDisplay(it.isActive, it.name.uppercase())
    }
        .filter { it.isActive }
        .collect{ println(it) }
}

suspend fun main(){
//    getData()
//    getData3()
//    makeNotes()
    getData4()
}