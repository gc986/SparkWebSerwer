package TestSpark

import spark.Spark.*

fun main(args: Array<String>) {
    println("Hello, World")
    println("show in web browser \"http://localhost:4567/hello\"")
    
    exception(Exception::class.java) { e, req, res -> e.printStackTrace() }

    get("/hello") { req, res -> "Hello World" }

    get("/addUser") { req, res ->
        val name = req.queryParams("name")

        val sql = SQLiteHelper()
        sql.conn()
        sql.write(name)
        sql.close()

        "User '$name' added"
    }

    get("/getUsers") { req, res ->
        val sql = SQLiteHelper()
        sql.conn()
        val users = sql.read()
        sql.close()

        users.toString()
    }

}