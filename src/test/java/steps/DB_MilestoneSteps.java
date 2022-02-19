package steps;

import core.DataBaseService;
import dbEntries.MilestonesTable;
import models.Milestone;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_MilestoneSteps {
    private MilestonesTable milestoneTable;
    public Milestone newMilestone;

    public static Logger logger = Logger.getLogger(DB_MilestoneSteps.class);

    public void createMilestoneTable(DataBaseService dataBaseService) {
        milestoneTable = new MilestonesTable(dataBaseService);

        milestoneTable.createTable();

        milestoneTable.addMilestone("Sprint1", "RF-1", "My new milestone", "3/20/2022", "3/30/2022");
        milestoneTable.addMilestone("Sprint1 Updated", "RF-1 Updated", "Updated milestone", "4/15/2022", "4/25/2022");
    }

    public Milestone createMilestoneFromDB(DataBaseService dataBaseService, int idMilestone) {
        milestoneTable = new MilestonesTable(dataBaseService);

        ResultSet rs = milestoneTable.getMilestoneByID(idMilestone);

        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String ms_references = rs.getString("ms_references");
                String description = rs.getString("description");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");

                logger.info("id: " + id);
                logger.info("name: " + name);
                logger.info("ms_references: " + ms_references);
                logger.info("description: " + description);
                logger.info("start_date: " + start_date);
                logger.info("end_date: " + end_date);

                newMilestone = Milestone.builder()
                        .name(name)
                        .description(description)
                        .references(ms_references)
                        .startDate(start_date)
                        .endDate(end_date)
                        .build();
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return newMilestone;
    }
}

