package tests.dataBaseTests;

import io.qameta.allure.Feature;
import mySqlDataBaseOperations.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.SqlUtils;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataBaseTests {

    @BeforeClass
    public void connectionToDbAndFirstUserRecord() throws SQLException {
        new SqlUtils().connectionToDb()
                .cleanDataBase();
        String testForNotNull = "NotNullTest" + RandomStringUtils.randomAlphanumeric(6);
        String mail = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        String age = "55";
        new Insert().insertNewUserWithNameAgeMail(testForNotNull, age, mail);
    }

    @AfterClass
    public void closeConnectionToDb() {
        new SqlUtils()
                .cleanDataBase()
                .close();
    }

    @Feature("DATABASE - INSERT")
    @Test
    public void insertUserCheck() throws SQLException {
        String testDataName = "testRecord" + RandomStringUtils.randomAlphanumeric(6).toLowerCase();
        String mail = RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@gmail.com";
        new Insert().insertNewUserWithNameAgeMail(testDataName, "55", mail);
        assertThat(new Select().getLastUser().name,
                containsString(testDataName));
        assertThat(new Select().getLastUser().mail,
                containsString(mail));
    }

    @Feature("DATABASE - UPDATE")
    @Test
    public void updateNameAgeByName() throws SQLException {
        String existedAge = "20";
        String existedNameRecord = "existedRecord" + RandomStringUtils.randomAlphanumeric(6).toLowerCase();
        String existedMailRecord = RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@gmail.com";
        String updatedName = "updatedRecord" + RandomStringUtils.randomAlphanumeric(6).toLowerCase();
        String updatedAge = RandomStringUtils.randomNumeric(2);
        String updatedMail = RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@gmail.com";
        new Insert()
                .insertNewUserWithNameAgeMail(existedNameRecord, existedAge, existedMailRecord);
        new Update().updateNameAgeByName(updatedName, updatedAge, updatedMail, existedNameRecord);
        assertThat(new Select().getLastUser().name,
                containsString(updatedName));
        assertThat(new Select().getLastUser().mail,
                containsString(updatedMail));
        assertThat(new Select().getLastUser().age,
                containsString(updatedAge));
    }

    @Feature("DATABASE - INSERT")
    @Test
    public void createNewUserRecordsByInsertToDb() throws SQLException {
        String testName = RandomStringUtils.randomAlphanumeric(6);
        String mail = RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@gmail.com";
        String age = "40";
        new Insert().insertNewUserWithNameAgeMail(testName, age, mail);
        assertThat(new Select().getLastUser().name,
                containsString(testName));
        assertThat(new Select().getLastUser().mail,
                containsString(mail));
        assertThat(new Select().getLastUser().age,
                containsString(age));
    }

    @Feature("DATABASE - DELETE")
    @Test
    public void deleteRecordsTests() throws SQLException {
        String testName = RandomStringUtils.randomAlphanumeric(6);
        String mail = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        String age = "40";
        new Insert().insertNewUserWithNameAgeMail(testName, age, mail);
        new Delete().deleteByUserName(testName);
        assertThat(new Select().getLastUser().name, is(not(equalTo(testName))));
    }

    @Test
    public void insertMultipleData() throws SQLException {
        String testName = RandomStringUtils.randomAlphanumeric(6);
        String mail = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        String age = "40";
        new Insert().insertMultipleData(500, testName, age, mail);
    }

    @Test
    public void createTable() throws SQLException {
        String tableName = "sample" + RandomStringUtils.randomAlphanumeric(6);
        new Table().createTable(tableName);
    }

    @Test
    public void dropTable() throws SQLException {
        String tableName = "testdb" + RandomStringUtils.randomAlphanumeric(6);
        new Table().createTable(tableName);
        new Table().dropTable(tableName);
    }
}