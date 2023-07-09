package org.example.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.example.models.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoviePage extends ParentPage {

    @FindBy(css = "h1 span")
    private WebElement titleMoviePage;
    @FindBy(css = "[data-testid=\"award_top-rated\"]")
    private WebElement positionMoviePage;
    @FindBy(css = "[data-testid = \"hero-rating-bar__aggregate-rating__score\"]")
    private WebElement ratingMoviePage;
    @FindBy(css = "[href*=\"tt_ov_rdat\" ]")
    private WebElement yearMoviePage;


    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public MoviePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getMovieInfoRating() throws Exception {
        WebElement rating = webDriver.findElement(By.cssSelector("[data-testid = \"hero-rating-bar__aggregate-rating__score\"]"));
        String[] movieRating = rating.getText().split("/");
        return movieRating[0].trim();
    }

    public String getMovieInfoYear() throws Exception {
        WebElement yearMoviePage = webDriver.findElement(By.cssSelector("[href*=\"tt_ov_rdat\" ]"));
        return yearMoviePage.getText();
    }

    public String getMovieInfoTitle() throws Exception {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".ipc-lockup-overlay__screen")));
        WebElement titleMoviePage = webDriver.findElement(By.cssSelector("h1 span"));
        return titleMoviePage.getText();
    }

    public String getMovieInfoPosition() throws Exception {
        String[] position = webDriver.findElement(By.cssSelector("[data-testid=\"award_top-rated\"]")).getText().split("#");
        return position[1];
    }

    public boolean moviePageIsOpened(String moviePosition) throws Exception {
        return webDriver.getCurrentUrl().contains(String.format("t_%s", moviePosition));
    }

    public Movie getMovieInfo() throws Exception {
        return new Movie(
                getMovieInfoTitle(),
                getMovieInfoPosition(),
                getMovieInfoYear(),
                getMovieInfoRating());
    }

}
