package org.example.app.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(){
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            getWeatherReport()
            println("Weather Report Printed!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { returnForecast() }
    val temperature = async { returnTemperature() }
    println("${forecast.await()} and ${temperature.await()}")
}


/*
We can take this weather example a step further and see how
coroutines can be useful in parallel decomposition of work.
Parallel decomposition involves taking a problem and breaking
it into smaller subtasks that can be solved in parallel.
When the results of the subtasks are ready, you can combine them into a final result

With coroutineScope(), even though the function
is internally doing work concurrently, it appears
to the caller as a synchronous operation because
coroutineScope won't return until all work is done
 */