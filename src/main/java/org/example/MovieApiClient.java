package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.interceptors.InterceptorRequest;
import org.example.services.MoviesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static org.example.pages.ParentPage.configProperties;

public class MovieApiClient {
    public MoviesService moviesService;

    public MovieApiClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new InterceptorRequest())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(configProperties.API_BASE_URL())
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesService = retrofit.create(MoviesService.class);
    }


    public static String getLastBitFromUrl(final String url) {
        return url.replaceFirst("API_KYE", "k_41npf8kq");
    }
}
