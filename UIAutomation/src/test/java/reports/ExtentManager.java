package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static boolean isSystemInfoSet = false;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // Generate timestamp for historical report folder
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportDir = System.getProperty("user.dir") + "/reports/" ;
            File dir = new File(reportDir);
            if (!dir.exists()) dir.mkdirs();

            // Set report file path
            String reportPath = reportDir + "/ExtentReport"+timestamp+".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Set system info only once
            if (!isSystemInfoSet) {
                extent.setSystemInfo("Tester", "Automation");
                extent.setSystemInfo("Environment", "QA");
                isSystemInfoSet = true;
            }
        }

        return extent;
    }
}