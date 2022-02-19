package dbEntries;


import core.DataBaseService;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class MilestonesTable {
    public static Logger logger = Logger.getLogger(MilestonesTable.class);

    DataBaseService dataBaseService;

    public MilestonesTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void createTable() {
        logger.info("Создаем таблицу milestones");

        String createTableSQL = "CREATE TABLE milestones (" +
                "id SERIAL PRIMARY KEY, " +
                "name CHARACTER VARYING(30), " +
                "ms_references CHARACTER VARYING(30), " +
                "description CHARACTER VARYING(50), " +
                "start_date CHARACTER VARYING(10), " +
                "end_date CHARACTER VARYING(10)" +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void dropTable() {
        logger.info("Удаляем таблицу milestones");

        String dropTableMilestonesSQL = "DROP TABLE milestones;";

        dataBaseService.executeSQL(dropTableMilestonesSQL);
    }

    public ResultSet getAllMilestones() {
        logger.info("Получаем все записи из таблицы milestones");

        String sql = "SELECT * FROM milestones ORDER BY id ASC;";

        return dataBaseService.executeQuery(sql);
    }

    public ResultSet getMilestoneByID(int id) {
        String sql = "SELECT * FROM milestones WHERE id = " + id + ";";

        return dataBaseService.executeQuery(sql);
    }

    public void addMilestone(String name, String ms_references, String description, String start_date, String end_date) {
        logger.info("Добавляем запись в таблицу milestones");

        String insertTableSQL = "INSERT INTO public.milestones(" +
                "name, ms_references, description, start_date, end_date)" +
                "VALUES ('" + name + "', '" + ms_references + "', '" + description + "', '" + start_date + "', '" + end_date + "');";

        dataBaseService.executeSQL(insertTableSQL);
    }
}
