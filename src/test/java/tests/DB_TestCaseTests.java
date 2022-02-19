package tests;

import baseEntity.BaseTest;
import enums.ProjectType;
import models.Project;
import models.TestCase;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.AddTestCasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DB_TestCaseTests extends BaseTest {

    private Project newProject;
    private TestCase addTestCase;
    private AddProjectPage addProjectPage;
    private AddTestCasePage addTestCasePage;
    public static String addTestCaseTitle;

    @Test
    public void createTestCaseTableTest() {
        db_testCaseSteps.createTestCaseTable(dataBaseService);
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
    public void addTestCaseTest() {
        addTestCase = db_testCaseSteps.createTestCaseFromDB(dataBaseService, 1);
        addTestCaseTitle = addTestCase.getTitle();

        addTestCasePage = testCaseSteps.addTestCase(newProject, addTestCase);
        addTestCasePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully added the new test case."));
    }

    @Test(dependsOnMethods = {"addProjectTest", "addTestCaseTest"})
    public void updateTestCaseTest() {
        TestCase updateTestCase = db_testCaseSteps.createTestCaseFromDB(dataBaseService, 2);

        addTestCasePage = testCaseSteps.updateTestCase(newProject, updateTestCase);
        addTestCasePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully updated the test case."));
    }

    @Test(dependsOnMethods = {"addProjectTest", "addTestCaseTest"})
    public void deleteTestCase() {
        addTestCasePage = testCaseSteps.deleteTestCase(newProject);
        addTestCasePage.getSuccessField().shouldBe(visible).shouldHave(text("Successfully flagged the test case as deleted."));
    }
}