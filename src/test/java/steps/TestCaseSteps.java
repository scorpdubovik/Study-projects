package steps;

import models.Project;
import models.TestCase;
import org.openqa.selenium.By;
import pages.AddTestCasePage;
import pages.DashboardPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static tests.DB_TestCaseTests.addTestCaseTitle;

public class TestCaseSteps {
    DashboardPage dashboardPage = new DashboardPage();
    AddTestCasePage addTestCasePage = new AddTestCasePage();

    public AddTestCasePage addTestCase(Project project, TestCase testCase) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $(By.id("navigation-suites")).click();
        $(By.id("sidebar-cases-add")).click();

        addTestCasePage.getTitleField().val(testCase.getTitle());
        addTestCasePage.getEstimateField().val(testCase.getEstimate());
        addTestCasePage.getReferencesField().val(testCase.getReferences());
        addTestCasePage.getSaveTestCaseButton().click();
        return addTestCasePage;
    }

    public AddTestCasePage updateTestCase(Project project, TestCase testCase) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $(By.id("navigation-suites")).click();
        $(byText(addTestCaseTitle)).click();
        $(".button-text").click();

        addTestCasePage.getTitleField().val(testCase.getTitle());
        addTestCasePage.getEstimateField().val(testCase.getEstimate());
        addTestCasePage.getReferencesField().val(testCase.getReferences());
        addTestCasePage.getSaveTestCaseButton().click();
        return addTestCasePage;
    }

    public AddTestCasePage deleteTestCase(Project project) {
        dashboardPage.openPage();
        $(byText(project.getName())).click();
        $(By.id("navigation-suites")).click();
        $(byText(addTestCaseTitle)).click();
        $(".button-text").click();
        $(By.xpath("//span[@class='button button-negative button-delete']")).click();
        $("a[onclick='this.blur(); App.Cases.confirmDeletion(false, false); return false;']").click();
        return addTestCasePage;
    }
}
