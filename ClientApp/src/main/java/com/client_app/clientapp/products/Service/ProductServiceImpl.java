package com.client_app.clientapp.products.Service;

import com.client_app.clientapp.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductsService {

    private static final String API_REQUEST_URL = "http://localhost:5050/Store/api/products";

    OkHttpClient httpClient = new OkHttpClient();
    Gson gson = new Gson();

    @Override
    public List <Product> getAllProductsFromAPI() {
        return requestToAPI(API_REQUEST_URL, new TypeToken <List <Product>>() {
        }.getType());
    }

    @Override
    public Product getProductFromAPIByID(Integer id) {
        return requestToAPI(API_REQUEST_URL + "/" + id, Product.class);
    }

    public <T> T requestToAPI(String URL, Type responseType) {
        Request request = new Request.Builder().url(URL).build();
        try (Response response = httpClient.newCall(request).execute()) {
            String responseData = response.body().string();
            return gson.fromJson(responseData, responseType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateProductViaAPI(Product product) {
        MediaType mediaType = MediaType.parse("application/json");
        String contentJson = gson.toJson(product);
        RequestBody body = RequestBody.create(contentJson, mediaType);

        Request request = new Request.Builder().url(API_REQUEST_URL).put(body).addHeader("Content-Type", "application/json").build();

        String responseBody = "None";
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.isSuccessful() ? response.body().string() : response.message();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    @Override
    public String deleteProductFromAPIByID(Integer id) {
        Request request = new Request.Builder().url(API_REQUEST_URL + "/" + id).delete().build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error!";
    }
}
