package com.insider.tests;

import com.insider.pages.CareersPage;
import com.insider.pages.HomePage;
import com.insider.pages.QAJobsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class InsiderTest extends BaseTest {

    @Test(priority = 1)
    public void testInsiderHomePageIsOpened() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage(BASE_URL);
        Assert.assertTrue(homePage.isHomePageOpened(),
                "Insider home page should be opened");
    }

    @Test(priority = 2)
    public void testCareersPageBlocks() {
        HomePage homePage = new HomePage(driver);
        CareersPage careersPage = new CareersPage(driver);

        homePage.openHomePage(BASE_URL);
        homePage.navigateToCareers();

        Assert.assertTrue(careersPage.isTeamsBlockDisplayed(),
                "Teams block should be displayed on Careers page");
        Assert.assertTrue(careersPage.isLocationsBlockDisplayed(),
                "Locations block should be displayed on Careers page");
        Assert.assertTrue(careersPage.isLifeAtInsiderBlockDisplayed(),
                "Life at Insider block should be displayed on Careers page");
    }

    @Test(priority = 3)
    public void testQAJobsFiltering() {
        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.openQAJobsPage(QA_JOBS_URL);
        qaJobsPage.clickSeeAllQAJobs();

        qaJobsPage.filterByLocation("Istanbul, Turkiye");
        qaJobsPage.filterByDepartment("Quality Assurance");

        Assert.assertTrue(qaJobsPage.areJobsDisplayed(), "Jobs list should be displayed after filtering");
    }

    @Test(priority = 4)
    public void testJobDetailsValidation() {
        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.openQAJobsPage(QA_JOBS_URL);
        qaJobsPage.clickSeeAllQAJobs();

        qaJobsPage.filterByLocation("Istanbul, Turkiye");
        qaJobsPage.filterByDepartment("Quality Assurance");

        Assert.assertTrue(qaJobsPage.areJobsDisplayed(), "There should be at least one job in the list");

        for (WebElement job : qaJobsPage.getAllJobs()) {
            String position = qaJobsPage.getJobPosition(job);
            String department = qaJobsPage.getJobDepartment(job);
            String location = qaJobsPage.getJobLocation(job);

            Assert.assertTrue(position.contains("Quality Assurance"),
                    String.format("Position '%s' should contain 'Quality Assurance'", position));
            Assert.assertTrue(department.contains("Quality Assurance"),
                    String.format("Department '%s' should contain 'Quality Assurance'", department));
            Assert.assertTrue(location.contains("Istanbul, Turkiye"),
                    String.format("Location '%s' should contain 'Istanbul, Turkiye'", location));
        }
    }

    @Test(priority = 5)
    public void testViewRoleRedirection() {
        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.openQAJobsPage(QA_JOBS_URL);
        qaJobsPage.clickSeeAllQAJobs();

        qaJobsPage.filterByLocation("Istanbul, Turkiye");
        qaJobsPage.filterByDepartment("Quality Assurance");

        Assert.assertTrue(qaJobsPage.areJobsDisplayed(), "There should be at least one job in the list");

        qaJobsPage.clickViewRoleForFirstJob();
        Assert.assertTrue(qaJobsPage.isQAApplicationPageOpened(), "Navigated application form should belong to QA job listing");
    }

}