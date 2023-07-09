package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage extends ParentPage {

    @FindBy(css = "#iconContext-menu")
    private WebElement contextMenu;
    @FindBy(id = "iconContext-menu")
    private WebElement contextMenuId;
    @FindBy(css = "#iconContext-movie")
    private WebElement contextMenuMovie;
    @FindBy(css = "[href = '/chart/top/?ref_=nv_mv_250']")
    private WebElement contextMenuTop250;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openBasePage() {
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DEFAULT_WAIT(), TimeUnit.SECONDS);
    }

    public void openTop250MoviesPage() {
        try {
            webDriver.get(baseUrl);
            WebElement menu = webDriver.findElement(By.cssSelector("#iconContext-menu"));
            menu.click();
            WebElement top250Movies = webDriver.findElement(By.cssSelector("[href = '/chart/top/?ref_=nv_mv_250']"));
            top250Movies.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
