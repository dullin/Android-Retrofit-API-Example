package com.example.jsonrestapitest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiService {

    public interface ServiceBlog {
        @GET("blogs")
        Call<List<Blog>> getBlogs();
    }

    ServiceBlog service;

    private static ApiService instance;

    private ApiService(){}

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl("https://expressapi-8byhzcvvv-hololink.vercel.app/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

            instance.service = retrofit.create(ServiceBlog.class);
        }
        return instance;
    }

}
