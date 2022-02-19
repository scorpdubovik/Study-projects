package dbEntries;


import core.DataBaseService;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class CustomersTable {
    public static Logger logger = Logger.getLogger(CustomersTable.class);

    DataBaseService dataBaseService;

    public CustomersTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void createTable() {
        logger.info("Создаем таблицу customers");

        String createTableSQL = "CREATE TABLE customers (" +
                "id SERIAL PRIMARY KEY, " +
                "firstname CHARACTER VARYING(30), " +
                "lastname CHARACTER VARYING(30), " +
                "email CHARACTER VARYING(255), " +
                "age INTEGER" +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void dropTable() {
        logger.info("Удаляем таблицу customers");

        String dropTableCustomersSQL = "DROP TABLE customers;";

        dataBaseService.executeSQL(dropTableCustomersSQL);
    }

    public ResultSet getAllCustomers() {
        logger.info("Получаем все записи из таблицы customers");

        String sql = "SELECT * FROM customers ORDER BY id ASC;";

        return dataBaseService.executeQuery(sql);
    }

    public ResultSet getCustomerByID(int id) {
        String sql = "SELECT * FROM customers WHERE id = " + id + ";";

        return dataBaseService.executeQuery(sql);
    }

    public void addCustomer(String firstname, String lastname, String email, int age) {
        logger.info("Добавляем запись в таблицу customers");

        String insertTableSQL = "INSERT INTO public.customers(" +
                "firstname, lastname, email, age)" +
                "VALUES ('" + firstname + "', '" + lastname + "', '" + email + "', " + age + ");";

        dataBaseService.executeSQL(insertTableSQL);
    }
}
