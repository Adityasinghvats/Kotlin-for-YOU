package org.example.app.dependenctinjection

//code modularity showcase by creating a new class
class Electric: Engine{
    override fun start() {
        println("Electric engine started")
    }
}


class Car2(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}

fun main() {
    val engine = GasEngine()
    val new_gen_engine = Electric()
    val car = Car2(new_gen_engine)
    car.start()
}