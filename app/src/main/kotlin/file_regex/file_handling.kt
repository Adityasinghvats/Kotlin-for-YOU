package file_regex

import java.io.File

fun main(){
//    val file = File("README.md")
//    println(file.length())

//    val file = File("file_created.txt")
//    file.createNewFile()
//    file.appendText("Hi , welcome to file system tutorial with Kotlin")

//    val folder = File("/Intro/basic")
//    val file = File(folder, "first.cpp")


//    <-----Create folders and files----->
//    if(!folder.exists()){
//        folder.mkdirs()
//    }
//    if(folder.exists()){
//        val text: String = "Hello, world"
//        file.appendText("#include<iostream> \nusing namespace std: \nint Coroutines.main(){ \n\tcout<<${text}; \n}")
//    }

//    <---Read project files--->
    val root = File(".")
     root.listFiles()?.forEach {
         println(it.name)
     }
}