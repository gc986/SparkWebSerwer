package TestSpark

import spark.Spark.*

fun main(args: Array<String>) {
    println("Hello, World")
    println("show in web browser \"http://localhost:4567/hello\"")
    
    exception(Exception::class.java) { e, req, res -> e.printStackTrace() }

    get("/hello") { req, res -> "Hello World" }

}