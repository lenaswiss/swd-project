package org.example.pages;

import org.example.models.Movie;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Top250MoviesPage extends ParentPage {

    @FindBy(css = "tbody tr")
    private List<WebElement> moviesList;
    @FindBy(css = "td.ratingColumn.imdbRating")
    private List<WebElement> ratings;
    @FindBy(css = "td.titleColumn a")
    private List<WebElement> titles;
    @FindBy(css = "td.titleColumn")
    private List<WebElement> positions;
    @FindBy(css = "td .secondaryInfo")
    private List<WebElement> years;
    @FindBy(css = "[data-testid=\"snackbase-close\"] #iconContext-clear")
    private WebElement modalTestWindow;
    @FindBy(css = "div.seen")
    private List<WebElement> loader;

    @FindBy(css = "#iconContext-star-inline")
    private List<WebElement> ratingsSecondView;
    @FindBy(css = ".ipc-metadata-list-summary-item .ipc-title__text")
    private List<WebElement> titlesAndPositions;
    @FindBy(css = "div.cli-title-metadata span.cli-title-metadata-item")
    private List<WebElement> moviesMetaSecondView;
    @FindBy(css = "[data-testid=\"snackbase-close\"] #iconContext-clear")

    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    public Top250MoviesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean testSideBarIsNotDisplayed() {
        try {
            webDriver.findElement(By.cssSelector("[data-testid=\"chart-layout-sidebar-links\"]")).isDisplayed();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean modelWindowIsNotPresent() {
        try {
            webDriver.findElement(By.cssSelector("[data-testid=\"snackbase-close\"] #iconContext-clear")).isDisplayed();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private String getMovieTitle(String title) {
        return new String(title.substring(title.indexOf(" "), title.indexOf("(")));
    }

    public List<Movie> getListOfTopMoviesRework() {
        List<Movie> listOfTopMovies = new ArrayList<>();
        try {
            if (!modelWindowIsNotPresent()) {
                webDriver.findElement(By.cssSelector("[data-testid=\"snackbase-close\"] #iconContext-clear")).click();
                List<WebElement> ratingsList = webDriver.findElements(By.cssSelector("span.ipc-rating-star.ipc-rating-star--base.ipc-rating-star--imdb.ratingGroup--imdb-rating"));
                List<String> yearsList = getYearsList(webDriver.findElements(By.cssSelector(".cli-title-metadata-item")));
                List<WebElement> positionsTitlesList = webDriver.findElements(By.cssSelector(".ipc-metadata-list-summary-item .ipc-title__text"));
                List<String> positionList = getPositionsList(positionsTitlesList);
                List<String> titleList = getTitlesList(positionsTitlesList);
                for (int i = 0; i < positionList.size(); i++) {
                    Movie movie = new Movie(titleList.get(i), positionList.get(i), yearsList.get(i), ratingsList.get(i).getText());
                    listOfTopMovies.add(movie);
                }
            } else {
                List<WebElement> ratingsList = webDriver.findElements(By.cssSelector("td.ratingColumn.imdbRating"));
                List<WebElement> positionList = webDriver.findElements(By.cssSelector("td.titleColumn"));
                List<WebElement> yearsList = webDriver.findElements(By.cssSelector("td .secondaryInfo"));
                List<WebElement> titleList = webDriver.findElements(By.cssSelector("td.titleColumn"));
                for (int i = 0; i < positionList.size(); i++) {
                    String yearFormat = yearsList.get(i).getText().replaceAll("^\\(|\\)$", "");
                    var positionFormat = positionList.get(i).getText().split(Pattern.quote("."));
                    Movie movie = new Movie(getMovieTitle(titleList.get(i).getText()).trim(), positionFormat[0], yearFormat, ratingsList.get(i).getText());
                    listOfTopMovies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfTopMovies;
    }

    private List<String> getYearsList(List<WebElement> elements) {
        List<String> yearsList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i += 3) {
            yearsList.add(elements.get(i).getText());
        }
        return yearsList;
    }

    private List<String> getTitlesList(List<WebElement> elements) {
        List<String> titlesList = new ArrayList<>();
        for (WebElement e : elements) {
            var stringList = e.getText().split(Pattern.quote("."));
            titlesList.add(stringList[1].trim());
        }
        return titlesList;
    }

    private List<String> getPositionsList(List<WebElement> elements) {
        List<String> positionsList = new ArrayList<>();
        for (WebElement e : elements) {
            var stringList = e.getText().split(Pattern.quote("."));
            positionsList.add(stringList[0]);
        }
        return positionsList;
    }

    public Movie findMovieByTitle(List<Movie> movieList, String movieTitle) {
        Movie foundMovie = new Movie();
        foundMovie = movieList.stream().filter(movie -> movie.title.equals(movieTitle)).findFirst()
                .orElseThrow(() -> new AssertionError("Movie was not fount"));
        return foundMovie;
    }

    public void openMoviePageByPosition(String position) {
        try {
            if (!testSideBarIsNotDisplayed()) {
                for (int i = 0; i < 3; i++) {
                    WebElement movie = webDriver.findElement(By.cssSelector(String.format("[href=\"/title/tt0068646/?ref_=chttp_t_%s\"]", position)));
                    movie.click();
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    WebElement movie = webDriver.findElement(By.cssSelector(String.format("[data-value = \"%s\"]", position)));
                    movie.findElement(By.xpath("..")).click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
