import config.*
import dao.*
import entity.*

fun main(args: Array<String>) {

    AppConfig.transactionManager.required {
        val user = User(null, "しおしお")
        val dao = dao<UserDao>()
        val result = dao.insert(user)
        println("result.entity = ${result.entity}")
        
        dao.batchInsert(listOf(
            User("siosio1"),
            User("siosio2"),
            User("siosio3")
        )).entities.forEach {
            println("user = ${it}")
        }
    }
}

inline fun <reified T : Any> dao(): T {
    val clazz = Thread.currentThread()
        .contextClassLoader
        .loadClass("${T::class.qualifiedName}Impl") as Class<T>
    return clazz.newInstance()
}