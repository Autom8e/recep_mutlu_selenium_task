package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class QAJobsPage extends BasePage {

    private final By seeAllQAJobsButton = By.linkText("See all QA jobs");
    private final By locationFilter = By.xpath("//select[@name='filter-by-location']");
    private final By departmentFilter = By.id("filter-by-department");
    private final By downArrow = By.id("select2-filter-by-location-container");
    private final By downArrowForDepartment = By.id("select2-filter-by-department-container");
    private final By jobsList = By.cssSelector(".position-list-item");
    private final By positionTitle = By.cssSelector(".position-title");
    private final By positionDepartment = By.cssSelector(".position-department");
    private final By positionLocation = By.cssSelector(".position-location");
    private final By viewRoleButton = By.linkText("View Role");
    private final By qaApplicationForm = By.tagName("h2");

    public QAJobsPage(WebDriver driver) {
        super(driver);
    }

    public void openQAJobsPage(String url) {
        driver.get(url);
        waitForPageLoad();
        handleCookieConsent();
    }

    public void clickSeeAllQAJobs() {
        WebElement button = waitForElement(seeAllQAJobsButton);
        scrollToElement(button);
        button.click();
        waitForPageLoad();
    }

    public void filterByLocation(String location) {
        WebElement locationDropdown = waitForElement(locationFilter);
        scrollToElement(locationDropdown);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(downArrowForDepartment, "Quality Assurance"));

        clickElement(downArrow);
        Select select = new Select(locationDropdown);
        select.selectByVisibleText(location);
    }

    public void filterByDepartment(String department) {
        WebElement departmentDropdown = waitForElement(departmentFilter);
        scrollToElement(departmentDropdown);

        clickElement(downArrowForDepartment);
        Select select = new Select(departmentDropdown);
        select.selectByVisibleText(department);

        waitForJobsLoad();
    }

    public void waitForJobsLoad() {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(jobsList, 0));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(jobsList, "Quality Assurance"));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Jobs list did not load within the expected time", e);
        }
    }

    public boolean areJobsDisplayed() {
        List<WebElement> jobs = driver.findElements(jobsList);
        return !jobs.isEmpty();
    }

    public List<WebElement> getAllJobs() {
        return driver.findElements(jobsList);
    }

    public String getJobPosition(WebElement job) {
        return job.findElement(positionTitle).getText();
    }

    public String getJobDepartment(WebElement job) {
        return job.findElement(positionDepartment).getText();
    }

    public String getJobLocation(WebElement job) {
        return job.findElement(positionLocation).getText();
    }

    public void clickViewRoleForFirstJob() {
        List<WebElement> jobs = getAllJobs();
        if (!jobs.isEmpty()) {
            WebElement firstJob = jobs.get(0);
            scrollToElement(firstJob);
            hover(firstJob);

            WebElement viewButton = firstJob.findElement(viewRoleButton);
            wait.until(ExpectedConditions.elementToBeClickable(viewButton));
            viewButton.click();

            switchToNewWindow();
            waitForPageLoad();
        }
    }

    public boolean isQAApplicationPageOpened() {
        wait.until(ExpectedConditions.urlContains("jobs.lever.co"));
        return driver.findElement(qaApplicationForm).getText().contains("Quality Assurance");
    }
}