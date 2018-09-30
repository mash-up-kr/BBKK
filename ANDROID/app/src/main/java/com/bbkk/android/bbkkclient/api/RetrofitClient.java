package com.bbkk.android.bbkkclient.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  private static Retrofit retrofit = null;
  private static int[] SUCCESS_CODES = {200, 201, 204};

  public static Retrofit getClient(String baseUrl, String uuidCode) {
    if (retrofit == null) {
      retrofit = RetrofitClient.createRetrofitClient(baseUrl, uuidCode);
    }
    return retrofit;
  }

  private static Retrofit createRetrofitClient(String baseUrl, String uuidCode) {
    Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
      .create();

    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    httpClientBuilder.addInterceptor(new Interceptor() {

      @Override
      public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder()
          .header("uuid", uuidCode)
          .build();

        Log.v("Retrofit","method: " + request.method() + " url: " + request.url() + " " + request.body() + " " + request.headers());

        Response response = chain.proceed(request);

        if (response.code() != 200 && response.code() != 201 && response.code() != 204) {
          throw new IOException(response.toString());
        }
        return response;
      }
    });

    return new Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(httpClientBuilder.build())
      .build();
  }
}
