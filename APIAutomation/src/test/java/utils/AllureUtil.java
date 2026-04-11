package utils;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class AllureUtil {

    public static void attachResponse(String response) {
        Allure.addAttachment("API Response", 
            new ByteArrayInputStream(response.getBytes()));
    }

    public static void attachRequest(String request) {
        Allure.addAttachment("API Request", 
            new ByteArrayInputStream(request.getBytes()));
    }
}