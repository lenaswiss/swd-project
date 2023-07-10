package org.example;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.ProfilesIni;
import org.example.pages.BasePage;
import org.example.pages.MoviePage;
import org.example.pages.Top250MoviesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.example.pages.ParentPage.configProperties;

public class BaseTest {

    public Logger logger = Logger.getLogger(getClass());
    public WebDriver webDriver;
    protected BasePage basePage;
    protected Top250MoviesPage top250MoviesPage;
    protected MoviePage moviePage;



    @Before
    public void setUp() throws Exception {
        webDriver = initDriverChrome();
        webDriver.manage().window();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DEFAULT_WAIT(), TimeUnit.SECONDS);
        logger.info("Browser was opened.");

        basePage = new BasePage(webDriver);
        top250MoviesPage = new Top250MoviesPage(webDriver);
        moviePage = new MoviePage(webDriver);
    }

    private WebDriver initDriver() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        firefoxOptions.setProfile(new ProfilesIni().getProfile("default"));
        File fileFF = new File("./drivers/geckodriver");
        System.setProperty("webdriver.gecko.driver", fileFF.getAbsolutePath());
        webDriver = new FirefoxDriver(firefoxOptions);
        return webDriver;
    }

    private WebDriver initDriverChrome() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        File fileFF = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver(chromeOptions);
//        webDriver = new ChromeDriver();
        return webDriver;
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed.");
    }
}
