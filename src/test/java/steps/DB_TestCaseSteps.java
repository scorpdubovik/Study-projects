package steps;

import core.DataBaseService;
import dbEntries.TestCasesTable;
import models.TestCase;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_TestCaseSteps {
    private TestCasesTable testCasesTable;
    private TestCase newTestCase;

    public static Logger logger = Logger.getLogger(DB_TestCaseSteps.class);

    public void createTestCaseTable(DataBaseService dataBaseService) {
        testCasesTable = new TestCasesTable(dataBaseService);

        testCasesTable.createTable();

        testCasesTable.addTestCase("Adding book to cart", "30s", "RF-1");
        testCasesTable.addTestCase("UPDATED adding book to cart", "1m", "RF-1 UPDATED");
    }

    public TestCase createTestCaseFromDB(DataBaseService dataBaseService, int idTestCase) {
        testCasesTable = new TestCasesTable(dataBaseService);

        ResultSet rs = testCasesTable.getTestCaseByID(idTestCase);

        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String estimate = rs.getString("estimate");
                String references = rs.getString("tc_references");

                logger.info("id: " + id);
                logger.info("title: " + title);
                logger.info("estimate: " + estimate);
                logger.info("references: " + references);

                newTestCase = TestCase.builder()
                        .title(title)
                        .estimate(estimate)
                        .references(references)
                        .build();
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return newTestCase;
    }
}

