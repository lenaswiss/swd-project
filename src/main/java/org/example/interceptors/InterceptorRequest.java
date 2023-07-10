package org.example.interceptors;

import okhttp3.*;

import java.io.IOException;
import static org.example.pages.ParentPage.configProperties;

public class InterceptorRequest implements Interceptor {
    private String API_KYE = "k_41npf8kq";

    @Override
    public Response intercept(Chain chain) throws IOException {

        var request = chain.request().newBuilder()
                .url(chain.request().url().toString().replaceAll("api_kye", API_KYE))
                .build();

        return chain.proceed(request);
    }

}