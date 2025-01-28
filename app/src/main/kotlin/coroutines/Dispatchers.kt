package org.example.app.coroutines

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}

/*If you have coroutines that were started on the main thread,
and you want to move certain operations off the main thread,
then you can use withContext to switch the dispatcher being used for that work.
Choose appropriately from the available dispatchers: Main, Default, and IO
depending on the type of operation it is. Then that work can be assigned
to a thread (or group of threads called a thread pool) designated for
that purpose. Coroutines can suspend themselves, and the dispatcher
also influences how they resume.
 */