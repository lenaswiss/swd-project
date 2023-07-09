package org.example;

import org.example.models.Movie;
import org.example.models.Title;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ApiTests {
    MovieApiClient apiClient = new MovieApiClient();
    SoftAssert softAssert = new SoftAssert();
    Assertion assertion = new Assertion();

    @Test
    public void top5MoviesTest() throws IOException {
        var topFiveMoviesList = apiClient.moviesService.getTop250Movies().execute().body().movies.subList(0, 5);
        Movie targetedMovie = topFiveMoviesList.stream().filter(m -> m.title.equals("The Godfather")).findFirst().orElseThrow(() -> new AssertionError("Movie was not fount"));
        var targetedMovieTitle = apiClient.moviesService.getItemTitle(targetedMovie.id).execute().body();
        assertion.assertEquals(targetedMovie.title, targetedMovieTitle.title, "Titles are not equal. Expected: Titles should be the same on the top250Movies page and on the Movie page.");
        assertion.assertEquals(targetedMovie.year, targetedMovieTitle.year, "Years are not equal. Expected: Year should be the same on the top250Movies page and on the Movie page.");
        assertion.assertEquals(targetedMovie.rating, targetedMovieTitle.ratings.rating, String.format("Ratings are not equal. Expected: Rating should be equal on the top250Movies page and on the Movie page. Expected: %s . Actual: %s .", targetedMovie.toString(), targetedMovieTitle.toString()));
    }

    /**
     * Нужно сформировать коллекцию из всех 250 топ фильмов в виде объектов класса Movies.
     * После этого зайти на страницу случайного фильма и проверить что показатели фильма
     * (Position, Title, Year, Rating) на странице соответствуют полученным ранее с общей страницы.
     */
    @Test
    public void movieInfoTest() throws IOException {
        List<Movie> topFiveMoviesList = apiClient.moviesService.getTop250Movies().execute().body().movies;
        Movie randomMovie = topFiveMoviesList.get(new Random().nextInt(250));
        Title randomMovieTitle = apiClient.moviesService.getItemTitle(randomMovie.id).execute().body();
        assertion.assertEquals(randomMovie.title, randomMovieTitle.title, "Titles are not equal. Expected: Titles should be the same on the top250Movies page and on the Movie page.");
        assertion.assertEquals(randomMovie.year, randomMovieTitle.year, "Years are not equal. Expected: Year should be the same on the top250Movies page and on the Movie page.");
        assertion.assertEquals(randomMovie.rating, randomMovieTitle.ratings.rating, String.format("Ratings are not equal. Expected: Rating should be equal on the top250Movies page and on the Movie page. Expected: %s . Actual: %s .", randomMovieTitle.toString(), randomMovie.toString()));
    }
}
