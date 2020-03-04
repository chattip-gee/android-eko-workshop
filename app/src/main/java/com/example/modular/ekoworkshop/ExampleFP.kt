package com.example.modular.ekoworkshop

import arrow.core.Either
import arrow.core.Option
import arrow.core.some

//First Class Function
val myFunction: () -> Unit = { println("[FP] - First Class Function : Hello Function") }

//Higher Order Function
fun getStringFromNetwork(callback: (String) -> Unit) {
    val string = "[FP] - Higher Order Function : String from network"
    callback(string)
}

//Pure Function
fun add(i1: Int, i2: Int): Int {
    return i1 + i2
}

//Arrow-kt
fun extractValueByOption(name: String): String {
    val someString: Option<String> = name.some()
    return someString.fold({ "I am no one" }, { "My name is $it" })
}

fun extractValueByEither(value: String): String {
    val result: Either<Exception, String> = Either.right(value)
    return result.fold({ Exception().toString() }, { it })
}

fun runExampleFP() {
    //First Class Function
    myFunction()

    //Higher Order Function
    getStringFromNetwork({ println(it) })
    getStringFromNetwork { println(it) }
    getStringFromNetwork(::myPrint)

    //Pure Function
    println("[FP] - Pure Function : " + add(1, 2))

    //Arrow-kt
    println("[FP] - Arrow-kt : " + extractValueByOption("Gee"))
    println("[FP] - Arrow-kt : " + extractValueByEither("EkoApp"))
}

fun myPrint(value: String) {
    println(value)
}