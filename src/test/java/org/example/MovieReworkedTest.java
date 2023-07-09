package org.example;

import org.example.models.Movie;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class MovieReworkedTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void topMoviePageTest() throws Exception{
        basePage.openTop250MoviesPage();
        List<Movie> top250MoviesList = top250MoviesPage.getListOfTopMoviesRework();
        Movie foundMovie = top250MoviesPage.findMovieByTitle(top250MoviesList, "The Godfather");
        top250MoviesPage.openMoviePageByPosition(foundMovie.position);
        Movie moviePageInfo = moviePage.getMovieInfo();
        softAssert.assertTrue(foundMovie.toString().equals(moviePageInfo.toString()), "Information about the movie is wrong");
    }

        @Test
    public void moviePageTest() throws Exception{
        basePage.openTop250MoviesPage();
        List<Movie> top250MoviesList = top250MoviesPage.getListOfTopMoviesRework();
        Movie randomMovieFromTopList = top250MoviesList.get(new Random().nextInt(250));
        top250MoviesPage.openMoviePageByPosition(randomMovieFromTopList.position);
        softAssert.assertTrue(moviePage.moviePageIsOpened(randomMovieFromTopList.position), "Movie page was not opened.");
        Movie movieFromMoviePage = moviePage.getMovieInfo();
        softAssert.assertTrue(movieFromMoviePage.toString().equals(randomMovieFromTopList.toString()),
                String.format("Information about movie is wrong, expected %s, but get %s", movieFromMoviePage.toString(), randomMovieFromTopList));
    }
}
