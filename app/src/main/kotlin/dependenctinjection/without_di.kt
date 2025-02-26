package org.example.app.dependenctinjection

interface Engine{
    fun start()
}
class GasEngine:Engine {
    override fun start() {
        println("Gas engine started")
    }
}

class Car{
    private val engine = GasEngine()

    fun start(){
        engine.start()
    }
}
fun main(){
    val car = Car()
    car.start()
}