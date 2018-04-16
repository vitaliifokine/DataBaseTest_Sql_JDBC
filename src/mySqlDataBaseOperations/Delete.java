package mySqlDataBaseOperations;

import io.qameta.allure.Step;
import utils.SqlUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    @Step
    public Delete deleteByUserName(String username) throws SQLException {
        String sql = "delete from users where name=?;";
        PreparedStatement preparedStatement =
                SqlUtils.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);
        int rowsAffected = preparedStatement.executeUpdate();
        return this;
    }
}
