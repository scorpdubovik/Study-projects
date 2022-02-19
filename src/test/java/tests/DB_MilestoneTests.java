package tests;

import baseEntity.BaseTest;
import enums.ProjectType;
import models.Milestone;
import models.Project;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.MilestonePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DB_MilestoneTests extends BaseTest {

    private Project newProject;
    private Milestone addMilestone;
    private MilestonePage milestonePage;
    private AddProjectPage addProjectPage;
    public static String addMilestoneName;

    @Test
    public void createMilestoneTableTest() {
        db_milestoneSteps.createMilestoneTable(dataBaseService);
    }

    @Test
    public void addProjectTest() {
        newProject = Project.builder()
                .name("Project2022")
                .announcement("My project")
                .isShowAnnouncement(true)
                .typeOfProject(ProjectType.SINGLE_SUITE_MODE)
                .build();

        addProjectPage = projectSteps.addProject(newProject);
        addProjectPage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully added the new project."));
    }

    @Test(dependsOnMethods = "addProjectTest")
    public void addMilestoneTest() {
        addMilestone = db_milestoneSteps.createMilestoneFromDB(dataBaseService, 1);
        addMilestoneName = addMilestone.getName();

        milestonePage = milestoneSteps.addMilestone(newProject, addMilestone);
        milestonePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully added the new milestone."));
    }

    @Test(dependsOnMethods = {"addProjectTest", "addMilestoneTest"})
    public void updateMilestoneTest() {
        Milestone updateMilestone = db_milestoneSteps.createMilestoneFromDB(dataBaseService, 2);

        milestonePage = milestoneSteps.updateMilestone(newProject, updateMilestone);
        milestonePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully updated the milestone."));
    }

    @Test(dependsOnMethods = {"addProjectTest", "addMilestoneTest", "updateMilestoneTest"})
    public void deleteMilestoneTest() {
        milestonePage = milestoneSteps.deleteMilestone(newProject);
        milestonePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully deleted the milestone (s)."));
    }
}