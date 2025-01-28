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

            //error handling at runblocking or sequential level ,
            //programme won't run further
//            try{
//                println(getWeatherAndMood())
//            }catch (e:AssertionError){
//                println("Error in run blocking: ${e.message}")
//            }

            println(getWeatherAndMood())
            println("Weather Report Printed!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}
suspend fun getWeatherAndMood() = coroutineScope {
    val forecast = async { returnForecast() }
    val mood = async {
        try {
            returnMood()
        }catch (e:AssertionError){
            println("Error in coroutinescope: ${e}")
            "{No temperature found}"
        }
    }
    "${forecast.await()} and ${mood.await()}"
}
/*delay to 500 milliseconds for the getTemperature() method,
so that the exception will occur before the other
getForecast() function can complete its work.

If parent coroutine gets cancelled due to an error
in one of child coroutines , it will propagate teh error and
cancel other child as well with AssertionError.
 */

suspend fun returnMood(): String {
    delay(500)
    throw AssertionError("Invalid mood data")
    return "Happy"
}