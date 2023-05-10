package com.client_app.clientapp.products.Service;

import com.client_app.clientapp.entity.HttpMethods;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.lang.reflect.Type;

public class HttpHelper {

    public static Request.Builder makeRequestBuilder(RequestBody body, String API_REQUEST_URL, HttpMethods httpMethod) {
        Request.Builder requestBuilder = new Request.Builder().url(API_REQUEST_URL).addHeader("Content-Type", "application/json");
        if (httpMethod.equals(HttpMethods.POST)) {
            requestBuilder.post(body);
        } else if (httpMethod.equals(HttpMethods.PUT)) {
            requestBuilder.put(body);
        } else {
            throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
        }
        return requestBuilder;
    }

    public static  <T> T requestToAPI(String URL, OkHttpClient httpClient, Gson gson, Type responseType) {
        Request request = new Request.Builder().url(URL).build();
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            String responseData = response.body().string();
            return gson.fromJson(responseData, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
