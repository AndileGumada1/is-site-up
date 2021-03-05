package com.andile.isthesiteup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect";

     @GetMapping("/check")
     public String getUrlStatusMessage(String url){
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            //open the connection
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();//cast the
            connection.setRequestMethod("GET");
            int responseCodeCategory = connection.getResponseCode() / 100;

            if (responseCodeCategory != 2 || responseCodeCategory != 3){
                returnMessage = SITE_IS_DOWN;
            }
            else {
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {

            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }
}
