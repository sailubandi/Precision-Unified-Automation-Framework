package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object response = result.getAttribute("response");

        if (response != null) {
            Allure.addAttachment(
                    "Failure Response",
                    new ByteArrayInputStream(response.toString().getBytes())
            );
        }
    }
}