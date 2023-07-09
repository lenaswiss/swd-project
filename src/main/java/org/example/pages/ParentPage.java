package org.example.pages;

import dev.failsafe.internal.util.Assert;
import org.example.libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    protected WebDriver webDriver;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + " was displayed");
            return state;
        } catch (Exception e) {
            logger.info("ERROR : Attempt to display " + getElementName(webElement) + "FAILED");
            return false;
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = webElement.getTagName();
        return elementName;
    }

    protected void isElementVisible(WebElement webElement) {
        Assert.isTrue(isElementDisplayed(webElement), getElementName(webElement) + " is not visible");
    }
}
