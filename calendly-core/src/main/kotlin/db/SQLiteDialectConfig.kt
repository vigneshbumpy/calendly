/*
package db

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.hibernate.dialect.Dialect
import org.hibernate.dialect.identity.IdentityColumnSupport
import org.hibernate.dialect.identity.IdentityColumnSupportImpl

@Configuration
class SQLiteDialectConfig {
    @Bean
    fun sqliteDialect(): Dialect {
        return SQLiteDialect()
    }
}

class SQLiteDialect : org.hibernate.dialect.SQLiteDialect() {
    override fun getIdentityColumnSupport(): IdentityColumnSupport {
        return SQLiteIdentityColumnSupport()
    }
}

class SQLiteIdentityColumnSupport : IdentityColumnSupportImpl() {
    override fun supportsIdentityColumns(): Boolean = true
    override fun getIdentitySelectString(table: String?, column: String?, type: Int): String =
        "select last_insert_rowid()"
    override fun getIdentityColumnString(type: Int): String = "integer"
}*/
