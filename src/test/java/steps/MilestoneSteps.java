package steps;

import models.Milestone;
import models.Project;
import org.openqa.selenium.By;
import pages.DashboardPage;
import pages.MilestonePage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static tests.DB_MilestoneTests.addMilestoneName;

public class MilestoneSteps {
    private MilestonePage milestonePage = new MilestonePage();
    private DashboardPage dashboardPage = new DashboardPage();

    public MilestonePage addMilestone(Project project, Milestone milestone) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $(By.id("navigation-overview-addmilestones")).click();

        milestonePage.getNameField().val(milestone.getName());
        milestonePage.getReferencesField().val(milestone.getReferences());
        milestonePage.getDescriptionField().val(milestone.getDescription());
        milestonePage.getStartDateField().val(milestone.getStartDate());
        milestonePage.getEndDateField().val(milestone.getEndDate());
        milestonePage.getAddMilestoneButton().click();
        return milestonePage;
    }

    public MilestonePage updateMilestone(Project project, Milestone milestone) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $(byText(addMilestoneName)).click();
        $(".toolbar-button.toolbar-button-last.content-header-button.button-edit").click();
        milestonePage = new MilestonePage();

        milestonePage.getNameField().val(milestone.getName());
        milestonePage.getReferencesField().val(milestone.getReferences());
        milestonePage.getDescriptionField().val(milestone.getDescription());
        milestonePage.getStartDateField().val(String.valueOf(milestone.getStartDate()));
        milestonePage.getEndDateField().val(String.valueOf(milestone.getEndDate()));
        milestonePage.getAddMilestoneButton().click();
        return milestonePage;
    }

    public MilestonePage deleteMilestone(Project project) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $("#navigation-milestones").click();
        $(".icon-small-delete").click();
        $(byXpath("//a[@class='button button-left button-positive button-disabled button-hidden button-no-margin-right dialog-action-secondary']/preceding-sibling::a")).click();
        return milestonePage;
    }
}
