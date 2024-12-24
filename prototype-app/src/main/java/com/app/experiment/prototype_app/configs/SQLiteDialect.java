package com.app.experiment.prototype_app.configs;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.dialect.unique.DefaultUniqueDelegate;
import org.hibernate.dialect.unique.UniqueDelegate;
import org.hibernate.engine.spi.RowSelection;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.type.StandardBasicTypes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        // Register column types specific to SQLite
        registerColumnType(Types.BIT, "boolean");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "text");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "text");  // Change CLOB to TEXT in SQLite
        registerColumnType(Types.BOOLEAN, "boolean");

        // Register functions for SQLite
        registerFunction("concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
        registerFunction("substring", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl(); // Standard identity column support
    }


    @Override
    public String getDropForeignKeyString() {
        return ""; // SQLite does not support dropping foreign keys
    }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return String.format("alter table %s add constraint %s foreign key (%s) references %s (%s)",
                referencedTable, constraintName, String.join(", ", foreignKey), referencedTable, String.join(", ", primaryKey));
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return String.format("alter table %s add constraint %s primary key (%s)", constraintName);
    }

    @Override
    public String getForUpdateString() {
        return ""; // SQLite does not support "for update"
    }

    @Override
    public boolean supportsUnionAll() {
        return true; // SQLite supports UNION ALL
    }

    @Override
    public boolean hasAlterTable() {
        return false; // SQLite does not support ALTER TABLE for adding or removing constraints
    }

    @Override
    public boolean dropConstraints() {
        return false; // SQLite does not support dropping constraints
    }

    @Override
    public String getAddColumnString() {
        return "add column"; // SQLite syntax for adding columns
    }

    @Override
    public String getForUpdateString(String aliases) {
        return ""; // SQLite does not support "for update" with aliases
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false; // SQLite does not support outer join for update
    }

    @Override
    public String getDropTableString(String tableName) {
        return "drop table if exists " + tableName; // SQLite syntax for dropping tables
    }

    @Override
    public String getCascadeConstraintsString() {
        return ""; // SQLite does not support cascade constraints
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true; // SQLite supports "if exists" before the table name
    }

    @Override
    public boolean supportsIfExistsAfterTableName() {
        return false; // SQLite does not support "if exists" after the table name
    }

    @Override
    public boolean supportsColumnCheck() {
        return false; // SQLite does not support column checks
    }

    @Override
    public boolean supportsTableCheck() {
        return false; // SQLite does not support table checks
    }

    @Override
    public boolean supportsCascadeDelete() {
        return false; // SQLite does not support cascade delete
    }

    @Override
    public UniqueDelegate getUniqueDelegate() {
        return new DefaultUniqueDelegate(this); // Default unique constraint handling
    }

    @Override
    public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
        return null; // No specific delegate for SQLException conversion
    }
}