package com.calendly.db

import org.hibernate.dialect.Dialect
import org.hibernate.dialect.identity.IdentityColumnSupport
import org.hibernate.dialect.identity.IdentityColumnSupportImpl
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
        registerColumnType(Types.TIMESTAMP, "datetime")
        registerColumnType(Types.BINARY, "blob")
        registerColumnType(Types.VARBINARY, "blob")
        registerColumnType(Types.LONGVARBINARY, "blob")
        registerColumnType(Types.BLOB, "blob")
        registerColumnType(Types.CLOB, "clob")
        registerColumnType(Types.BOOLEAN, "integer")
    }

    override fun getIdentityColumnSupport(): IdentityColumnSupport {
        return SQLiteIdentityColumnSupport()
    }

    override fun hasAlterTable(): Boolean {
        return false
    }

    override fun dropConstraints(): Boolean {
        return false
    }

    override fun getAddColumnString(): String {
        return "add column"
    }

    override fun getForUpdateString(): String {
        return ""
    }

    override fun supportsOuterJoinForUpdate(): Boolean {
        return false
    }

    override fun getDropForeignKeyString(): String {
        return ""
    }

    override fun getDropSequenceString(sequenceName: String): String {
        return "drop sequence if exists $sequenceName"
    }

    override fun getAddForeignKeyConstraintString(
        constraintName: String?,
        foreignKey: Array<String>?,
        referencedTable: String?,
        primaryKey: Array<String>?,
        referencesPrimaryKey: Boolean
    ): String {
        return ""
    }

    override fun getAddPrimaryKeyConstraintString(constraintName: String?): String {
        return ""
    }

    override fun supportsIfExistsBeforeTableName(): Boolean {
        return true
    }

    override fun supportsCascadeDelete(): Boolean {
        return false
    }

    private class SQLiteIdentityColumnSupport : IdentityColumnSupportImpl() {
        override fun supportsIdentityColumns(): Boolean {
            return true
        }

        fun getIdentityColumnString(): String {
            return "integer"
        }

        override fun getIdentitySelectString(table: String?, column: String?, type: Int): String {
            return "select last_insert_rowid()"
        }
    }
}




