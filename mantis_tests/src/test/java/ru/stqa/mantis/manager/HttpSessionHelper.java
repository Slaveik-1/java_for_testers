package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import okhttp3.JavaNetCookieJar;
import org.openqa.selenium.By;

public class HttpSessionHelper extends HelperBase{

    OkHttpClient client;


    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager()))
                .build();
    }


    public void login(String username, String pass) {
        RequestBody request1FormBody = new FormBody.Builder()
                .add("username", username)
                .build();
        Request request1 = new Request.Builder()
                .url(String.format("%s/login_page.php",manager.getProperties("web.baseURL")))
                .post(request1FormBody)
                .build();
        try (Response response1 = client.newCall(request1).execute()) {
            if (!response1.isSuccessful()) throw new RuntimeException("Unexpected code " + response1);
            String body = response1.body().string();
            String loginTokenPrefix = "<input type=\"hidden\" name=\"login_token\" value=\"";
            int tokenStart = body.indexOf(loginTokenPrefix) + loginTokenPrefix.length();
            int tokenEnd = body.indexOf("\"", tokenStart);
            String token = body.substring(tokenStart, tokenEnd);

            RequestBody request2FormBody = new FormBody.Builder()
                    .add("login_token", token)
                    .add("username", username)
                    .add("password", pass)
                    .build();
            Request request2 = new Request.Builder()
                    .url(String.format("%s/login.php",manager.getProperties("web.baseURL")))
                    .post(request2FormBody)
                    .build();
            try (Response response2 = client.newCall(request2).execute()) {
                if (!response2.isSuccessful()) throw new RuntimeException("Unexpected code " + response2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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


