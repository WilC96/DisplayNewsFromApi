package com.example.newsappmvvm.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    @Volatile
    private var RETROFIT_INSTANCE: Retrofit? = null
    private const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "3ad2ea1a69ad41a9b2f59fd8bc624dc8"

    private fun getClient() : Retrofit {
        return RETROFIT_INSTANCE ?: synchronized(this) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            val builder = OkHttpClient.Builder()
//            builder.addInterceptor(interceptor)
            val client = OkHttpClient.Builder()
//                    .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            RETROFIT_INSTANCE = retrofit
            retrofit
        }
    }

    fun<T> getNewsService(service: Class<T>): T {
        return getClient().create(service)
    }
}

// first fragment
// https://newsapi.org/v2/top-headlines?country=us&apiKey=3ad2ea1a69ad41a9b2f59fd8bc624dc8

// second fragment
// https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=3ad2ea1a69ad41a9b2f59fd8bc624dc8

// http://api.themoviedb.org/3/movie/top_rated?sort_by=popularity?&api_key=7f94c23a923d05379eeb7b8ca74a181a

//    Use Case 1:
//    Get the top news in fragment 1;
//    documentation for API
//    https://newsapi.org/docs/endpoints/top-headlines
//
//    API to use
//    https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
//
//    Use Case 2:
//
//    	In second fragment screen use the following API
//    https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=API_KEY
//    	On click of the item in first fragment get the "id" and pass as a parameter to above API and display another list
//
//
//    Use Case 3:
//    On click of the second fragment item; open a WebView to display the news
//    Use URL parameter and pass its value using implicit intent


// ================================================================================================
// ================================================================================================
// ================================================================================================


//    public static final String API_KEY = "7f94c23a923d05379eeb7b8ca74a181a";
//    public static final String BASE_URL="https://api.themoviedb.org/3/";

//    Map<String, String> parameters = new HashMap<>();
//    parameters.put("sort_by", "popularity");
//    parameters.put("api_key", ApiClient.API_KEY);
//
//    ApiInterface myApi = ApiClient.getClient().create(ApiInterface.class);
//    myApi.getMovieDetails(parameters)
//

//    http://api.themoviedb.org/3/movie/top_rated?sort_by=popularity?&api_key=7f94c23a923d05379eeb7b8ca74a181a

//    @GET("movie/top_rated")
//    Observable<MovieResult> getMovieDetails(@QueryMap Map<String, String> param);

//    public static Retrofit getClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//        }
//        if(retrofit==null){
//            retrofit= new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//        }
//        return retrofit;
//    }