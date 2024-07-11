package com.calendly.db

import org.hibernate.dialect.Dialect
import org.hibernate.dialect.function.SQLFunctionTemplate
import org.hibernate.dialect.function.StandardSQLFunction
import org.hibernate.dialect.function.VarArgsSQLFunction
import org.hibernate.dialect.identity.IdentityColumnSupport
import org.hibernate.dialect.identity.IdentityColumnSupportImpl
import org.hibernate.type.StringType

class SQLiteDialect : Dialect() {
    init {
        registerColumnType(java.sql.Types.BIT, "integer")
        registerColumnType(java.sql.Types.TINYINT, "tinyint")
        registerColumnType(java.sql.Types.SMALLINT, "smallint")
        registerColumnType(java.sql.Types.INTEGER, "integer")
        registerColumnType(java.sql.Types.BIGINT, "bigint")
        registerColumnType(java.sql.Types.FLOAT, "float")
        registerColumnType(java.sql.Types.REAL, "real")
        registerColumnType(java.sql.Types.DOUBLE, "double")
        registerColumnType(java.sql.Types.NUMERIC, "numeric")
        registerColumnType(java.sql.Types.DECIMAL, "decimal")
        registerColumnType(java.sql.Types.CHAR, "char")
        registerColumnType(java.sql.Types.VARCHAR, "varchar")
        registerColumnType(java.sql.Types.LONGVARCHAR, "longvarchar")
        registerColumnType(java.sql.Types.DATE, "date")
        registerColumnType(java.sql.Types.TIME, "time")
        registerColumnType(java.sql.Types.TIMESTAMP, "timestamp")
        registerColumnType(java.sql.Types.BINARY, "blob")
        registerColumnType(java.sql.Types.VARBINARY, "blob")
        registerColumnType(java.sql.Types.LONGVARBINARY, "blob")
        registerColumnType(java.sql.Types.BLOB, "blob")
        registerColumnType(java.sql.Types.CLOB, "clob")
        registerColumnType(java.sql.Types.BOOLEAN, "integer")

        registerFunction("concat", VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""))
        registerFunction("mod", SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"))
        registerFunction("substr", StandardSQLFunction("substr", StringType.INSTANCE))
        registerFunction("substring", StandardSQLFunction("substr", StringType.INSTANCE))
    }

    override fun supportsLimit(): Boolean = true
    override fun getLimitString(query: String, hasOffset: Boolean): String {
        return StringBuffer(query.length + 20).append(query)
            .append(if (hasOffset) " limit ? offset ?" else " limit ?")
            .toString()
    }
    override fun getIdentityColumnSupport(): IdentityColumnSupport {
        return SQLiteIdentityColumnSupport()
    }
    override fun supportsCurrentTimestampSelection(): Boolean = true
    override fun isCurrentTimestampSelectStringCallable(): Boolean = false
    override fun getCurrentTimestampSelectString(): String = "select current_timestamp"
    override fun supportsUnionAll(): Boolean = true
    override fun hasAlterTable(): Boolean = false
    override fun dropConstraints(): Boolean = false
    override fun getAddColumnString(): String = "add column"
    override fun getForUpdateString(): String = ""
    override fun supportsOuterJoinForUpdate(): Boolean = false
    override fun getDropForeignKeyString(): String = throw UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect")
    override fun getAddForeignKeyConstraintString(constraintName: String?, foreignKey: Array<String>, referencedTable: String?, primaryKey: Array<String>, referencesPrimaryKey: Boolean): String = throw UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect")
    override fun getAddPrimaryKeyConstraintString(constraintName: String?): String = throw UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect")
    override fun supportsIfExistsBeforeTableName(): Boolean = true
    override fun supportsCascadeDelete(): Boolean = false
}

private class SQLiteIdentityColumnSupport : IdentityColumnSupportImpl() {
    override fun supportsIdentityColumns(): Boolean = true

    override fun getIdentitySelectString(table: String?, column: String?, type: Int): String =
        "select last_insert_rowid()"

    override fun getIdentityColumnString(type: Int): String = "integer"
}