package org.example.app.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(){
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            println(getWeatherMood())
            println("Weather Report Printed!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}
suspend fun getWeatherMood() = coroutineScope {
    val forecast = async { returnForecast() }
    val mood = async {
        returnMood()
    }

    delay(200)
    mood.cancel()
    "${forecast.await()}"
}

//rest of code is in coroutine_exception file

