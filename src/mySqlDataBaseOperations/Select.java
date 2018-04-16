package mySqlDataBaseOperations;

import io.qameta.allure.Step;
import utils.SqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    @Step
    public UserStructure getLastUser() throws SQLException {
        String query = "select name, age, mail from users;";
        Statement st = SqlUtils.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);
        result.last();
        String name = result.getString(1);
        String age = result.getString(2);
        String mail = result.getString(3);
        return new UserStructure(name, age, mail);
    }

    @Step
    public Select selectNameAgeMail() {
        System.out.println("[OUTPUT FROM SELECT]");
        String query = "select name, age, mail from users;";
        try {
            Statement st = SqlUtils.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String mail = rs.getString("mail");
                System.out.println(name + "   " + age + "   " + mail);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return this;
    }
}
