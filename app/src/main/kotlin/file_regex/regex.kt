package file_regex

fun main(){
//    val pattern = Regex("ad[e-i]tya(abc|012)$", RegexOption.IGNORE_CASE)
//    println(pattern.containsMatchIn("hello, adetya012"))

    /*
    * [abc] -> anyone of a, b or c
    * [a-g] -> anything in range of {a, b}
    * [^abc] -> negate , except a
    * (ty) -> get exact same order and chars
    * (ty)? -> expecting once or never
    * ^[yz] -> must start with y or z
    * [mn]$ -> must end with m or n
    * In idea use context actions to check RegExp
    * (abc|012) -> either abc or 012
    * . -> single char for anything
    * .* -> many single char
    * \\s -> 1 single white space
    * \\s+ -> one or more spaces
    * \\w* -> any word between
    * [a-zA-Z1-9] -> any word
    * */

    val email = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$",RegexOption.IGNORE_CASE)
    println(email.containsMatchIn("aditya6.@gmail.com"))

}