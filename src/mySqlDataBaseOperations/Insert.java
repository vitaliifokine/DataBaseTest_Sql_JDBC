package mySqlDataBaseOperations;

import io.qameta.allure.Step;
import utils.SqlUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

    @Step
    public Insert insertNewUserWithNameAgeMail(String name, String age, String mail) throws SQLException {
        String sql = "insert into users (name, age, mail) values (?, ?, ?); ";
        PreparedStatement preparedStatement =
                SqlUtils.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, age);
        preparedStatement.setString(3, mail);
        int rowsAffected = preparedStatement.executeUpdate();
        return this;
    }

    @Step
    public Insert insertMultipleData(int quantity, String name, String age, String mail) throws SQLException {
        String sql = "insert into users (name, age, mail) values (?, ?, ?);";
        PreparedStatement ps = SqlUtils.getConnection().prepareStatement(sql);
        final int batchSize = 1000;
        int count = 0;

        for (int i = 0; i < quantity; i++) {
            ps.setString(1, name);
            ps.setString(2, age);
            ps.setString(3, mail);
            ps.addBatch();

            if (++count % batchSize == 0) {
                ps.executeBatch();
            }
        }
        ps.executeBatch(); // insert remaining records
        return this;
    }
}
