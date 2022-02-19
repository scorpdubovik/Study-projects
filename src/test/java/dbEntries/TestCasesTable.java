package dbEntries;


import core.DataBaseService;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class TestCasesTable {
    public static Logger logger = Logger.getLogger(TestCasesTable.class);

    DataBaseService dataBaseService;

    public TestCasesTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void createTable() {
        logger.info("Создаем таблицу testCases");

        String createTableSQL = "CREATE TABLE testCases (" +
                "id SERIAL PRIMARY KEY, " +
                "title CHARACTER VARYING(30), " +
                "estimate CHARACTER VARYING(10), " +
                "tc_references CHARACTER VARYING(30) " +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void dropTable() {
        logger.info("Удаляем таблицу testCases");

        String dropTableTestCasesSQL = "DROP TABLE testCases;";

        dataBaseService.executeSQL(dropTableTestCasesSQL);
    }

    public ResultSet getAllTestCases() {
        logger.info("Получаем все записи из таблицы testCases");

        String sql = "SELECT * FROM testCases ORDER BY id ASC;";

        return dataBaseService.executeQuery(sql);
    }

    public ResultSet getTestCaseByID(int id) {
        String sql = "SELECT * FROM testCases WHERE id = " + id + ";";

        return dataBaseService.executeQuery(sql);
    }

    public void addTestCase(String title, String estimate, String references) {
        logger.info("Добавляем запись в таблицу testCases");

        String insertTableSQL = "INSERT INTO public.testCases(" +
                "title, estimate, tc_references)" +
                "VALUES ('" + title + "', '" + estimate + "', '" + references + "');";

        dataBaseService.executeSQL(insertTableSQL);
    }
}
