package com.insider.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected final By acceptCookiesButton = By.id("wt-cli-accept-all-btn");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    protected void handleCookieConsent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            cookieButton.click();
            Thread.sleep(500);
        } catch (Exception e) {
            // Cookie banner might not appear, continue
        }
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickElement(By locator) {
        waitForElement(locator).click();
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    protected void waitForPageLoad() {
        wait.until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected void switchToNewWindow() {
        String mainWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}