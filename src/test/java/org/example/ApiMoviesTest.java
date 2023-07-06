package org.example;

import org.example.models.Movie;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ApiMoviesTest {

    MovieApiClient apiClient = new MovieApiClient();
    public static final String API_KYE = "k_41npf8kq";
    SoftAssert softAssert =  new SoftAssert();

    /**
     * Что нам нужно  - автотест, который
     * Будет заходить на IMDB (https://www.imdb.com/)
     * Открывать список топ фильмов IMDb Top 250 Movies
     * Собирать список топ 5 фильмов - их название, рейтинг и год выхода
     * Сохранять этом список обьектами типа Movies, которые имеют поля Position, Title, Year, Rating
     * Проверять что в нем есть “Крестный отец”
     * Переходить на страницу фильма  “Крестный отец” и проверять, что данные
     * (год, название и рейтинг) отображаются те же что и на странице списка фильмов
     */
    @Test
    public void top5MoviesTest() throws IOException {
        var topFiveMoviesList = apiClient.moviesService.getTop250Movies(API_KYE).execute().body().movies.subList(0, 5);
        Movie targetedMovie = new Movie();
        targetedMovie = topFiveMoviesList.stream().filter(movie -> movie.title.equals("The Godfather")).findFirst()
                .orElseThrow(() -> new AssertionError("Movie was not fount"));
        var targetedMovieTitle = apiClient.moviesService.getItemTitle(API_KYE, targetedMovie.id).execute().body();
//        softAssert.assertTrue(targetedMovie.toString().equals());
    }
}
