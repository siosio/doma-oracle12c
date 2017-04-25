package config

import dialect.*
import oracle.jdbc.pool.*
import org.seasar.doma.jdbc.*
import org.seasar.doma.jdbc.dialect.*
import org.seasar.doma.jdbc.tx.*
import javax.sql.*

object AppConfig : Config {

    private val dialect: Dialect = Oracle12Dialect

    override fun getCommandImplementors(): CommandImplementors = Oracle12CommandImplementors

    private val dataSource by lazy {
        val oracleDataSource = OracleDataSource()
        oracleDataSource.user = "siosio"
        oracleDataSource.setPassword("siosio")
        oracleDataSource.url = "???"

        LocalTransactionDataSource(oracleDataSource)
    }

    override fun getTransactionManager(): TransactionManager {
        return LocalTransactionManager(dataSource.getLocalTransaction(jdbcLogger))
    }

    override fun getDataSource(): DataSource = dataSource

    override fun getDialect(): Dialect = dialect
}