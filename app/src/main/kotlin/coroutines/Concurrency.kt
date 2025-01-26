package org.example.app.coroutines


import kotlin.system.*
import kotlinx.coroutines.*

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch { printForecast() }
            launch { printTemperature() }
            printMood()
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}
suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}

suspend fun printMood(){
    println("Holiday Mood")
}

/*
Use the launch() function from the coroutines library to
launch a new coroutine. To execute tasks concurrently,
add multiple launch() functions to your code so that multiple
coroutines can be in progress at the same time.

launch() has fire and forget nature , it will return execution
even if work is not finished, to solve this we use async , await
You fire off a new coroutine with launch(),
and don't have to worry about when its work is finished.

Use the async() function from the coroutines library if
you care about when the coroutine finishes and need a return value from it.

The key insight here for structured concurrency is that
you can take multiple concurrent operations and put it
into a single synchronous operation, where concurrency
is an implementation detail. The only requirement on the
calling code is to be in a suspend function or coroutine.
Other than that, the structure of the calling code doesn't
need to take into account the concurrency details.

* */