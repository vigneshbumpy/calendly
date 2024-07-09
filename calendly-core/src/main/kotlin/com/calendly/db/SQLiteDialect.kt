package com.example.demo.config

import org.hibernate.dialect.Dialect
import org.hibernate.dialect.function.SQLFunctionTemplate
import org.hibernate.dialect.function.StandardSQLFunction
import org.hibernate.dialect.function.VarArgsSQLFunction
import org.hibernate.type.StringType
import java.sql.Types

class SQLiteDialect : Dialect() {
    init {
        registerColumnType(Types.BIT, "integer")
        registerColumnType(Types.TINYINT, "tinyint")
        registerColumnType(Types.SMALLINT, "smallint")
        registerColumnType(Types.INTEGER, "integer")
        registerColumnType(Types.BIGINT, "bigint")
        registerColumnType(Types.FLOAT, "float")
        registerColumnType(Types.REAL, "real")
        registerColumnType(Types.DOUBLE, "double")
        registerColumnType(Types.NUMERIC, "numeric")
        registerColumnType(Types.DECIMAL, "decimal")
        registerColumnType(Types.CHAR, "char")
        registerColumnType(Types.VARCHAR, "varchar")
        registerColumnType(Types.LONGVARCHAR, "longvarchar")
        registerColumnType(Types.DATE, "date")
        registerColumnType(Types.TIME, "time")
        registerColumnType(Types.TIMESTAMP, "timestamp")
        registerColumnType(Types.BINARY, "blob")
        registerColumnType(Types.VARBINARY, "blob")
        registerColumnType(Types.LONGVARBINARY, "blob")
        registerColumnType(Types.BLOB, "blob")
        registerColumnType(Types.CLOB, "clob")
        registerColumnType(Types.BOOLEAN, "integer")

        registerFunction("concat", VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""))
        registerFunction("mod", SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"))
        registerFunction("substr", StandardSQLFunction("substr", StringType.INSTANCE))
        registerFunction("substring", StandardSQLFunction("substr", StringType.INSTANCE))
    }

    fun supportsIdentityColumns(): Boolean = true
    fun hasDataTypeInIdentityColumn(): Boolean = false
    fun getIdentityColumnString(): String = "integer"
    fun getIdentitySelectString(): String = "select last_insert_rowid()"
    override fun supportsLimit(): Boolean = true
    fun supportsTemporaryTables(): Boolean = true
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
    override fun getAddForeignKeyConstraintString(constraintName: String?, foreignKey: Array<String>?, referencedTable: String?, primaryKey: Array<String>?, referencesPrimaryKey: Boolean): String = throw UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect")
    override fun getAddPrimaryKeyConstraintString(constraintName: String?): String = throw UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect")
    override fun supportsIfExistsBeforeTableName(): Boolean = true
    override fun supportsCascadeDelete(): Boolean = false
}