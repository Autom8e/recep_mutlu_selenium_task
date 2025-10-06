package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareersPage extends BasePage {

    private final By locationsBlock = By.cssSelector("#location-slider");
    private final By teamsBlock = By.cssSelector("#career-find-our-calling");
    private final By lifeAtInsiderBlock = By.cssSelector(".swiper-wrapper  ");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLocationsBlockDisplayed() {
        WebElement element = waitForElement(locationsBlock);
        scrollToElement(element);
        return element.isDisplayed();
    }

    public boolean isTeamsBlockDisplayed() {
        WebElement element = waitForElement(teamsBlock);
        scrollToElement(element);
        return element.isDisplayed();
    }

    public boolean isLifeAtInsiderBlockDisplayed() {
        WebElement element = waitForElement(lifeAtInsiderBlock);
        scrollToElement(element);
        return element.isDisplayed();
    }
}