package jp.snowgirl.test_program.config

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.MysqlDialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import javax.annotation.Resource
import javax.naming.NamingException
import javax.naming.InitialContext
import javax.sql.DataSource


object AppConfig : Config {
    private val JNDI_DATASOURCE = "jdbc/sample"

    private val dialect = MysqlDialect()

    private val dataSource = createDataSource()

    private val transactionManager = LocalTransactionManager(
            dataSource.getLocalTransaction(jdbcLogger))

    private fun createDataSource(): LocalTransactionDataSource {
        try {
//            return LocalTransactionDataSource(InitialContext.doLookup<DataSource>(JNDI_DATASOURCE))
            return LocalTransactionDataSource(
                    "jdbc:mysql://127.0.0.1/app", "appuser", "appuser");
        } catch (e: NamingException) {
            throw RuntimeException(e)
        }

    }

    override fun getDialect() = dialect

    override fun getDataSource() = dataSource

    override fun getTransactionManager() = transactionManager

    override fun getNaming() = Naming.SNAKE_LOWER_CASE
}