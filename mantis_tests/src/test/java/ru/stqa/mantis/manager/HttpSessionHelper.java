package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import okhttp3.JavaNetCookieJar;

public class HttpSessionHelper extends HelperBase{

    OkHttpClient client;


    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager()))
                .build();
    }


    public void login(String username, String pass) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", pass)
                .build();

        Request request = new Request.Builder()
                .url(String.format("%s/login.php",manager.getProperties("web.baseURL")))
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.getProperties("web.baseURL"))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
