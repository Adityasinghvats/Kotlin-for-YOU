package org.example.app.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(){
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            val forecast : Deferred<String> = async {
                returnForecast()
            }
            val temperature : Deferred<String> = async {
                returnTemperature()
            }
            println("${forecast.await()} and ${temperature.await()}")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}

suspend fun returnForecast():String{
    delay(1000)
    return "Sunny"
}

suspend fun returnTemperature():String {
    delay(1000)
    return "30\u00b0C"
}

/*
Neat! You created two coroutines that ran concurrently to get the
forecast and temperature data. When they each completed, they returned a value.
Then you combined the two return values into a single print statement: Sunny 30°C.
* */