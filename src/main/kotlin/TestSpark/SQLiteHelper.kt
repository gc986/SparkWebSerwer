package TestSpark

import java.sql.*


class SQLiteHelper {

    private var conn: Connection? = null
    private var statmt: Statement? = null
    private var resSet: ResultSet? = null

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    @Throws(ClassNotFoundException::class, SQLException::class)
    fun conn() {
        conn = null
        Class.forName("org.sqlite.JDBC")
        conn = DriverManager.getConnection("jdbc:sqlite:TEST1.s3db")

        println("База Подключена!")

        createDB()
    }

    // --------Создание таблицы--------
    @Throws(ClassNotFoundException::class, SQLException::class)
    private fun createDB() {
        statmt = conn?.createStatement()
        statmt?.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text);")

        println("Таблица создана или уже существует.")
    }

    // --------Заполнение таблицы--------
    @Throws(SQLException::class)
    fun write(name: String) {
        statmt?.execute("INSERT INTO 'users' ('name') VALUES ('$name'); ")
        println("Пользователь добавлен")
    }

    // -------- Вывод таблицы--------
    @Throws(ClassNotFoundException::class, SQLException::class)
    fun read():List<String> {
        resSet = statmt?.executeQuery("SELECT * FROM users")

        val names = ArrayList<String>()
        resSet?.let {resSet ->
            while (resSet.next()) {
                val id = resSet.getInt("id")
                val name = resSet.getString("name")
                println("ID = $id")
                println("name = $name")
                println()
                names.add(name)
            }
        }

        println("Таблица выведена")
        return names
    }

    // --------Закрытие--------
    @Throws(ClassNotFoundException::class, SQLException::class)
    fun close() {
        conn?.close()
        statmt?.close()
        resSet?.close()

        println("Соединения закрыты")
    }

}