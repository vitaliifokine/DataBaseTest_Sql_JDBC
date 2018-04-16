package mySqlDataBaseOperations;

import utils.SqlUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table {
    public void createTable(String tableName) throws SQLException {
        PreparedStatement preparedStatement = null;
        String createTableSQL =  "CREATE TABLE " + tableName + " " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        preparedStatement = SqlUtils.getConnection().prepareStatement(createTableSQL);
        preparedStatement.executeUpdate();
    }

    public void dropTable(String tableName) throws SQLException {
        PreparedStatement preparedStatement = null;
        String dropTableSQL =  "DROP TABLE `mydbtest`.`" + tableName +"`; ";
        preparedStatement = SqlUtils.getConnection().prepareStatement(dropTableSQL);
        preparedStatement.executeUpdate();
    }
}
