package baseEntity;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.DataBaseService;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import steps.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public DataBaseService dataBaseService;

    protected User user;
    protected ProjectSteps projectSteps;
    protected MilestoneSteps milestoneSteps;
    protected TestCaseSteps testCaseSteps;
    protected DB_MilestoneSteps db_milestoneSteps;
    protected DB_TestCaseSteps db_testCaseSteps;

    @BeforeClass
    public void setUp() {
        //for fine-tuning:
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

        // Настройка slf4j
        org.apache.log4j.BasicConfigurator.configure();

        // setupConnection
        dataBaseService = new DataBaseService();

        // Настройка Selenide
        Configuration.baseUrl = ReadProperties.getUrl();
        Configuration.browser = ReadProperties.getBrowserName().toLowerCase();
        Configuration.startMaximized = false;
        Configuration.fastSetValue = true;
        Configuration.timeout = 8000;
        Configuration.headless = ReadProperties.isHeadless();

        projectSteps = new ProjectSteps();
        milestoneSteps = new MilestoneSteps();
        testCaseSteps = new TestCaseSteps();
        db_milestoneSteps = new DB_MilestoneSteps();
        db_testCaseSteps = new DB_TestCaseSteps();

        // Login
        open("/");
        LoginPage loginPage = new LoginPage();

        loginPage.getUsernameField().val(ReadProperties.getUsername());
        loginPage.getPasswordField().setValue(ReadProperties.getPassword());
        loginPage.getLoginButton().click();
    }

    @AfterClass
    public void closePage() {
        closeWebDriver();

        // closeConnection
        dataBaseService.closeConnection();
    }
}