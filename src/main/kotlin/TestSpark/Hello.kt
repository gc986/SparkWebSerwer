package TestSpark

import spark.Spark.*

fun main(args: Array<String>) {
    println("Hello, World")
    
    exception(Exception::class.java) { e, req, res -> e.printStackTrace() }

    get("/hello") { req, res -> "Hello World" }

}