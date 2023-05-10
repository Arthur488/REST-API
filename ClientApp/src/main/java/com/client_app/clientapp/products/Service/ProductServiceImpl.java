package com.client_app.clientapp.products.Service;

import com.client_app.clientapp.entity.HttpMethods;
import com.client_app.clientapp.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductsService {

    private static final String API_REQUEST_URL = "http://localhost:5050/Store/api/products";

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final Gson gson = new Gson();

    @Override
    public List <Product> getAllProductsFromAPI() {
        return HttpHelper.requestToAPI(API_REQUEST_URL, httpClient, gson, new TypeToken <List <Product>>() {
        }.getType());
    }

    @Override
    public Product getProductFromAPIByID(Integer id) {
        return HttpHelper.requestToAPI(API_REQUEST_URL + "/" + id, httpClient, gson, Product.class);
    }

    @Override
    public void saveProductViaAPI(Product product, HttpMethods httpMethod) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String serializedProduct = gson.toJson(product);
        RequestBody body = RequestBody.create(serializedProduct, mediaType);
        Request.Builder requestBuilder = HttpHelper.makeRequestBuilder(body, API_REQUEST_URL, httpMethod);
        httpClient.newCall(requestBuilder.build()).execute().close();
    }

    @Override
    public void deleteProductFromAPIByID(Integer id) throws IOException {
        Request request = new Request.Builder().url(API_REQUEST_URL + "/" + id).delete().build();
        httpClient.newCall(request).execute().close();
    }
}
