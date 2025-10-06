package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By insiderLogo = By.cssSelector("a.navbar-brand img");
    private final By companyMenu = By.xpath("//a[contains(text(),'Company')]");
    private final By careersLink = By.xpath("//a[contains(text(),'Careers')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
        waitForPageLoad();
        handleCookieConsent();
    }

    public boolean isHomePageOpened() {
        return isElementDisplayed(insiderLogo);
    }

    public void navigateToCareers() {
        WebElement companyMenuElement = waitForElement(companyMenu);
        hover(companyMenuElement);
        clickElement(careersLink);
        waitForPageLoad();
    }
}