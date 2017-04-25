import config.*
import oracle.jdbc.pool.*
import java.sql.*

fun main(args: Array<String>) {
    getConnection().use { 
        it.prepareStatement("insert into users (name) values (?)", intArrayOf(1)).use { st ->
            (1..10).forEach { 
                st.setString(1, "name_$it")
                st.addBatch()
            }
            st.executeBatch()
            st.generatedKeys.use { 
                generateSequence { 
                    it
                }.takeWhile(ResultSet::next).forEach {
                    println("id = ${it.getInt(1)}")
                }
            }
        }
    }
}

private fun getConnection() : Connection {
    val oracleDataSource = OracleDataSource()
    oracleDataSource.user = "siosio"
    oracleDataSource.setPassword("siosio")
    oracleDataSource.url = "???"
    return oracleDataSource.connection
}