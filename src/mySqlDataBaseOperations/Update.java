package mySqlDataBaseOperations;

import io.qameta.allure.Step;
import utils.SqlUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    @Step
    public Update updateNameAgeByName(String name, String age, String mail, String existedName) throws SQLException {
        String sql = "update users set name =? , age=?, mail=? where name=?";
        PreparedStatement updateStatement =
                SqlUtils.getConnection().prepareStatement(sql);
        updateStatement.setString(1, name);
        updateStatement.setString(2, age);
        updateStatement.setString(3, mail);
        updateStatement.setString(4, existedName);
        int rowsAffected = updateStatement.executeUpdate();
        return this;
    }
}
