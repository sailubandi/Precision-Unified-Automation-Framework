package hooks;

import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import reports.ExtentManager;
import reports.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Hooks {

    private static boolean isSystemInfoSet = false;

    @Before
    public void setup(Scenario scenario) {
        // Initialize driver
        DriverFactory.initDriver();

        // Create Extent test
        ExtentTestManager.setTest(
                ExtentManager.getInstance().createTest(scenario.getName())
        );

        // Set system info only once
        if (!isSystemInfoSet) {
            ExtentManager.getInstance().setSystemInfo("Tester", "Automation");
            isSystemInfoSet = true;
        }
    }

    @After
    public void teardown(Scenario scenario) {
    	
        if (DriverManager.getDriver() != null) {
            try {
                if (scenario.isFailed()) {
                    // Take screenshot as file
                    File srcFile = ((TakesScreenshot) DriverManager.getDriver())
                            .getScreenshotAs(OutputType.FILE);

                    // Create folder if not exists
                    File screenshotDir = new File("screenshots");
                    if (!screenshotDir.exists()) screenshotDir.mkdirs();

                    // File name: scenarioName_timestamp.png
                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String fileName = "Screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                            + "_" + timestamp + ".png";
                    File destFile = new File(fileName);
                    FileUtils.copyFile(srcFile, destFile);

                    // Attach to Cucumber
                    byte[] screenshotBytes = FileUtils.readFileToByteArray(destFile);
                    scenario.attach(screenshotBytes, "image/png", scenario.getName());

                    // Attach to Extent Report (Base64)
                    String base64Image = Base64.getEncoder().encodeToString(screenshotBytes);
                    ExtentTestManager.getTest().fail("Test Failed",
                            com.aventstack.extentreports.MediaEntityBuilder
                                    .createScreenCaptureFromBase64String(base64Image)
                                    .build());

                } else {
                    ExtentTestManager.getTest().pass("Test Passed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                DriverManager.getDriver().quit();
                DriverManager.unload();
            }
        }

        // Flush Extent report and unload test
        ExtentManager.getInstance().flush();
        ExtentTestManager.unload();
    }
    
}
