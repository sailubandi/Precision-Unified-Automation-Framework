# Precision Unified Automation Framework

[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-blue.svg)](https://testng.org/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-green.svg)](https://cucumber.io/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-yellow.svg)](https://selenium.dev/)

A comprehensive hybrid automation framework that combines API and UI testing capabilities using modern tools and best practices. This framework is designed for enterprise-level testing with focus on maintainability, scalability, and ease of use.

## Overview

This framework provides a unified solution for testing web applications through both REST API endpoints and Selenium WebDriver UI automation. Built with Maven multi-module architecture, it supports parallel execution, detailed reporting, and scalable test management.

### Key Benefits
- **Unified Testing**: Single framework for both API and UI testing
- **BDD Approach**: Cucumber integration for business-readable tests
- **Parallel Execution**: Run tests concurrently for faster feedback
- **Comprehensive Reporting**: Multiple report formats for different stakeholders
- **Modular Architecture**: Clean separation of concerns
- **Easy Configuration**: Property-driven test configuration
- **Enterprise Ready**: Production-grade framework with best practices

## Architecture

```
HybridAutomation/
|-- APIAutomation/          # API testing module
|   |-- src/test/java/
|   |   |-- client/         # API client utilities
|   |   |   |-- ApiClient.java           # Centralized API client
|   |   |   |-- RequestBuilder.java      # HTTP request builder
|   |   |   `-- ResponseHandler.java     # Response processing
|   |   |-- config/         # Configuration management
|   |   |   |-- ConfigReader.java        # Properties reader
|   |   |   `-- ApiConfig.java           # API-specific config
|   |   |-- factory/        # Test data factories
|   |   |   |-- UserDataFactory.java     # User test data
|   |   |   |-- ProductDataFactory.java  # Product test data
|   |   |   `-- RequestDataFactory.java  # Request data
|   |   |-- stepdefinitions/ # Cucumber step definitions (TC1-TC6)
|   |   |   |-- TC1_GetAllProductsSteps.java
|   |   |   |-- TC2_GetSingleProductSteps.java
|   |   |   |-- TC3_CreateUserSteps.java
|   |   |   |-- TC4_DeleteUserSteps.java
|   |   |   |-- TC5_UpdateUserSteps.java
|   |   |   |-- TC6_NegativeValidationSteps.java
|   |   |   `-- CommonSteps.java          # Shared steps
|   |   |-- runner/         # TestNG Cucumber runner
|   |   |   `-- ApiTestRunner.java       # Test execution entry point
|   |   |-- utils/          # Utility classes
|   |   |   |-- TestDataUtils.java       # Test data utilities
|   |   |   |-- JsonUtils.java           # JSON processing
|   |   |   `-- SchemaValidator.java     # JSON schema validation
|   |   `-- constants/      # Test constants
|   |       `-- ApiEndpoints.java        # API endpoint definitions
|   |-- src/test/resources/
|   |   |-- features/       # Cucumber feature files
|   |   |   |-- TC1_GetAllProducts.feature
|   |   |   |-- TC2_GetSingleProduct.feature
|   |   |   |-- TC3_CreateUser.feature
|   |   |   |-- TC4_DeleteUser.feature
|   |   |   |-- TC5_UpdateUser.feature
|   |   |   `-- TC6_NegativeValidation.feature
|   |   |-- schemas/       # JSON schema validation
|   |   |   |-- product-list-schema.json
|   |   |   |-- search-result-schema.json
|   |   |   |-- user-response-schema.json
|   |   |   `-- error-response-schema.json
|   |   `-- config.properties # Test configuration
|   `-- testng-api.xml      # TestNG configuration
|
|-- UIAutomation/           # UI testing module
|   |-- src/test/java/
|   |   |-- PageObjects/    # Selenium Page Object Model
|   |   |   |-- HomePage.java             # Main page object
|   |   |   |-- LoginPage.java            # Login page
|   |   |   |-- RegisterPage.java         # Registration page
|   |   |   |-- CartPage.java             # Shopping cart
|   |   |   `-- ProductPage.java         # Product details
|   |   |-- constants/      # Test constants
|   |   |   |-- FrameworkConstants.java   # Global constants
|   |   |   `-- WaitConstants.java        # Wait timeouts
|   |   |-- stepdefinitions/ # Cucumber step definitions (TC1-TC5)
|   |   |   |-- TC1_RegisterSteps.java    # Registration flow
|   |   |   |-- TC2_LoginSteps.java        # Login flow
|   |   |   |-- TC3_InvalidLoginSteps.java # Negative login tests
|   |   |   |-- TC4_AddToCartSteps.java    # Cart operations
|   |   |   |-- TC5_RemoveFromCartSteps.java
|   |   |   `-- CommonUISteps.java        # Shared UI steps
|   |   |-- runner/         # TestNG Cucumber runner
|   |   |   `-- TestRunner.java           # UI test execution
|   |   |-- utils/          # Utility classes
|   |   |   |-- SeleniumUtils.java        # WebDriver utilities
|   |   |   |-- ScreenshotUtils.java      # Screen capture
|   |   |   `-- WaitUtils.java            # Wait strategies
|   |   `-- reports/        # Reporting utilities
|   |       `-- ExtentTestManager.java    # ExtentReports manager
|   |-- src/test/resources/
|   |   |-- Feature/        # Cucumber feature files
|   |   |   |-- Register.feature
|   |   |   |-- Login.feature
|   |   |   |-- Incorrectlogin.feature
|   |   |   |-- AddProductToCart.feature
|   |   |   `-- RemoveFromCart.feature
|   |   `-- config.properties # UI configuration
|   `-- testng-ui.xml       # TestNG configuration
|
|-- Common/                  # Shared utilities module
|   |-- src/main/java/       # Common utilities
|   |   |-- utils/           # Shared utilities
|   |   |   |-- ExcelReader.java          # Excel operations
|   |   |   |-- DateUtils.java            # Date formatting
|   |   |   |-- FileUtils.java            # File operations
|   |   |   `-- StringUtils.java          # String utilities
|   |   |-- exceptions/     # Custom exceptions
|   |   |   |-- FrameworkException.java   # Base exception
|   |   |   `-- DataNotFoundException.java
|   |   `-- config/          # Shared configuration
|   |       `-- FrameworkConfig.java     # Global config
|   |-- src/test/resources/ # Shared resources
|   |   |-- testdata/        # Test data files
|   |   `-- templates/       # Template files
|   `-- pom.xml              # Common module configuration
|
|-- pom.xml                  # Parent Maven configuration
|-- testng.xml              # Unified test execution
|-- testng-eclipse.xml      # Eclipse-specific configuration
`-- README.md               # This file
```

## Technology Stack

### Core Technologies
- **Java 8+** - Programming language
- **Maven 3.6+** - Build and dependency management
- **TestNG 7.9.0** - Test execution framework
- **Cucumber 7.15.0** - BDD framework
- **Selenium WebDriver 4.18.1** - UI automation
- **RestAssured 5.4.0** - API testing

### Supporting Libraries
- **Log4j2 2.22.1** - Logging framework
- **SLF4J 2.0.9** - Logging facade
- **Apache POI 5.2.5** - Excel operations
- **WebDriver Manager 5.8.0** - Browser driver management
- **Extent Reports 5.1.1** - HTML reporting
- **Allure TestNG 2.24.0** - Advanced reporting
- **Apache Commons Lang3 3.14.0** - Utility functions

## Features

### API Automation
- **RESTful API Testing** with RestAssured
- **JSON Schema Validation** for response verification
- **Test Data Management** with factory pattern
- **Configuration-driven** testing via properties
- **Parallel Execution** support
- **Allure Reporting** integration

### UI Automation
- **Selenium WebDriver** for cross-browser testing
- **Page Object Model** design pattern
- **Cucumber BDD** framework
- **Extent Reports** for detailed test reporting
- **WebDriver Manager** for automatic driver management
- **Parallel Test Execution**

### Shared Features
- **Maven Multi-Module** architecture
- **TestNG** test runner
- **Cucumber** BDD framework
- **Log4j2** logging framework
- **Apache POI** for Excel operations
- **SLF4J** logging facade

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- Chrome Browser (for UI tests)
- IDE (Eclipse/IntelliJ)

## Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/sailubandi/Precision-Unified-Automation-Framework.git
cd Precision-Unified-Automation-Framework
```

### 2. Prerequisites Setup
```bash
# Verify Java version
java -version

# Verify Maven installation
mvn -version

# Install Chrome browser (for UI tests)
# Chrome will be automatically managed by WebDriverManager
```

### 3. Build the Project
```bash
# Clean and compile all modules
mvn clean compile

# Run tests to verify setup
mvn clean test -Dmaven.test.failure.ignore=true
```

### 4. Run All Tests
```bash
# Execute both API and UI tests
mvn clean test

# Or with failure tolerance (recommended for first run)
mvn clean test -Dmaven.test.failure.ignore=true

# Run specific test suite
mvn clean test -Dtestng.xml=testng.xml
```

### 5. Run Individual Modules

#### API Tests Only
```bash
cd APIAutomation
mvn clean test -Dtestng.xml=testng-api.xml

# Run specific API test scenario
mvn test -Dcucumber.options="--tags @TC1"
```

#### UI Tests Only
```bash
cd UIAutomation
mvn clean test -Dtestng.xml=testng-ui.xml

# Run specific UI test scenario
mvn test -Dcucumber.options="--tags @Register"
```

### 6. Eclipse IDE Setup
```bash
# Import as Maven project
File -> Import -> Maven -> Existing Maven Projects

# Run tests from Eclipse
Right-click testng.xml -> Run As -> TestNG Suite
```

## Test Scenarios

### API Test Cases (TC1-TC6)

#### TC1: Get All Products List
- **Endpoint**: `GET /api/productsList`
- **Validation**: Status code 200, JSON schema, response body
- **Tags**: `@TC1 @Products @Smoke`

#### TC2: Get Single Product Details by Search
- **Endpoint**: `POST /api/searchProduct`
- **Validation**: Search with multiple parameters
- **Scenarios**: "top", "tshirt", "jean"
- **Tags**: `@TC2 @Search @Regression`

#### TC3: Create New User Account
- **Endpoint**: `POST /api/createAccount`
- **Validation**: User creation with test data factory
- **Expected**: Email already exists (400)
- **Tags**: `@TC3 @User @Critical`

#### TC4: Delete User Account
- **Endpoint**: `DELETE /api/deleteAccount`
- **Validation**: Account deletion with credentials
- **Expected**: Account not found (404)
- **Tags**: `@TC4 @User @Regression`

#### TC5: Update User Account
- **Endpoint**: `PUT /api/updateAccount`
- **Validation**: Account update with modified data
- **Expected**: Account not found (404)
- **Tags**: `@TC5 @User @Regression`

#### TC6: Negative Validation Tests
- **Invalid Email**: malformed email validation
- **Missing Fields**: required field validation
- **Invalid Search**: non-existent product search
- **Tags**: `@TC6 @Negative @Sanity`

### UI Test Cases (TC1-TC5)

#### TC1: User Registration Flow
- **Feature**: Complete user registration process
- **Steps**: Launch browser, navigate, register, verify
- **Validation**: Account creation, login verification
- **Tags**: `@TC1 @Register @Smoke`

#### TC2: User Login Flow
- **Feature**: User authentication process
- **Steps**: Login with valid credentials
- **Validation**: Successful login, user dashboard
- **Tags**: `@TC2 @Login @Smoke`

#### TC3: Invalid Login Validation
- **Feature**: Negative login scenarios
- **Steps**: Login with invalid credentials
- **Validation**: Error message display
- **Tags**: `@TC3 @Login @Negative`

#### TC4: Add Products to Cart
- **Feature**: E-commerce cart operations
- **Steps**: Browse products, add to cart, verify
- **Validation**: Cart contents, pricing, quantity
- **Tags**: `@TC4 @Cart @E2E`

#### TC5: Remove Products from Cart
- **Feature**: Cart management operations
- **Steps**: Add products, remove from cart
- **Validation**: Product removal, cart updates
- **Tags**: `@TC5 @Cart @Regression`

## Configuration

### API Configuration
Edit `APIAutomation/src/test/resources/config.properties`:

```properties
# API Configuration
api.base.url=https://api.example.com
api.timeout=10000
api.retry.count=3

# Test Data Configuration
test.user.name=John Doe
test.user.email=john.doe@test.com
test.user.password=Test123
test.user.title=Mr
test.user.birth_date=15
test.user.birth_month=January
test.user.birth_year=1990

# User Details
test.user.firstname=John
test.user.lastname=Doe
test.user.company=Tech Inc
test.user.address1=123 Main St
test.user.address2=Apt 4B
test.user.country=USA
test.user.zipcode=10001
test.user.state=NY
test.user.city=New York
test.user.mobile_number=1234567890

# Search Configuration
test.search.term.top=top
test.search.term.tshirt=tshirt
test.search.term.jean=jean
test.search.term.invalid=nonexistentproduct123
```

### UI Configuration
Edit `UIAutomation/src/test/resources/config.properties`:

```properties
# Browser Configuration
browser=chrome
headless=false
implicit.wait=10
explicit.wait=30
page.load.timeout=60

# Application Configuration
app.url=https://example.com
app.title=Example Website

# WebDriver Configuration
chrome.options=--disable-extensions,--disable-infobars,--disable-notifications
firefox.options=--disable-extensions,--disable-infobars

# Reporting Configuration
extent.report.path=reports/ExtentReport.html
screenshot.path=reports/screenshots/
video.recording=false

# Test Data Configuration
test.user.email=testuser@example.com
test.user.password=password123
test.user.name=Test User
```

## Code Examples

### API Test Example
```java
// TC1_GetAllProductsSteps.java
public class TC1_GetAllProductsSteps {
    
    @Given("the API endpoint is available for products")
    public void the_api_endpoint_is_available_for_products() {
        // Initialize API client
        apiClient = new ApiClient();
        apiClient.setBaseEndpoint(ApiEndpoints.PRODUCTS_LIST);
    }
    
    @When("I make a GET request to {string}")
    public void i_make_a_get_request_to(String endpoint) {
        response = apiClient.get(endpoint);
    }
    
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
    
    @And("the response should contain a valid products list")
    public void the_response_should_contain_a_valid_products_list() {
        List<Product> products = response.jsonPath().getList("products");
        assertThat(products, notNullValue());
        assertThat(products.size(), greaterThan(0));
    }
}
```

### UI Test Example
```java
// TC1_RegisterSteps.java
public class TC1_RegisterSteps {
    
    @Given("user launches browser")
    public void launchBrowser() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @And("user navigates to application")
    public void navigate() {
        driver.get(ConfigReader.get("app.url"));
    }
    
    @When("user clicks on Signup Login button")
    public void clickSignup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginButton();
    }
    
    @Then("home page should be visible")
    public void verifyHome() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not visible");
    }
}
```

### Test Data Factory Example
```java
// UserDataFactory.java
public class UserDataFactory {
    
    public static User createValidUser() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.name", "John Doe"));
        user.setEmail(ConfigReader.get("test.user.email", "john.doe@test.com"));
        user.setPassword(ConfigReader.get("test.user.password", "Test123"));
        user.setTitle(ConfigReader.get("test.user.title", "Mr"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date", "15"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month", "January"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year", "1990"));
        user.setFirstname(ConfigReader.get("test.user.firstname", "John"));
        user.setLastname(ConfigReader.get("test.user.lastname", "Doe"));
        user.setCompany(ConfigReader.get("test.user.company", "Tech Inc"));
        user.setAddress1(ConfigReader.get("test.user.address1", "123 Main St"));
        user.setAddress2(ConfigReader.get("test.user.address2", "Apt 4B"));
        user.setCountry(ConfigReader.get("test.user.country", "USA"));
        user.setZipcode(ConfigReader.get("test.user.zipcode", "10001"));
        user.setState(ConfigReader.get("test.user.state", "NY"));
        user.setCity(ConfigReader.get("test.user.city", "New York"));
        user.setMobile_number(ConfigReader.get("test.user.mobile_number", "1234567890"));
        return user;
    }
}
```

## Reports

### TestNG Reports
- **Location**: `target/surefire-reports/`
- **Format**: HTML and XML
- **Access**: Open `index.html` in browser
- **Content**: Test execution summary, failure details

```bash
# View TestNG reports
open target/surefire-reports/index.html
```

### Cucumber Reports
- **Location**: `target/cucumber-reports/`
- **Format**: HTML and JSON
- **Features**: Step-by-step execution, scenario status
- **Access**: `target/cucumber-reports/cucumber.html`

```bash
# Generate detailed Cucumber report
mvn test -Dcucumber.options="--plugin html:target/cucumber-report.html"
```

### Allure Reports (API)
- **Location**: `target/allure-results/`
- **Format**: Interactive HTML
- **Features**: Test history, attachments, categories
- **Generate**: Requires Allure command-line tool

```bash
# Install Allure (if not installed)
npm install -g allure-commandline

# Generate and view Allure report
allure serve target/allure-results

# Or generate static report
allure generate target/allure-results --clean -o allure-report
```

### Extent Reports (UI)
- **Location**: `target/extent-reports/`
- **Format**: Interactive HTML with screenshots
- **Features**: Test categorization, timeline, dashboard
- **Configuration**: Configurable via `ExtentTestManager`

```bash
# View ExtentReports
open target/extent-reports/ExtentReport.html
```

## Parallel Execution

The framework supports parallel execution at multiple levels for optimal performance:

### Test Level Parallelism
```xml
<!-- testng.xml -->
<suite name="Test Suite" parallel="tests" thread-count="3">
    <test name="API Tests">
        <!-- API test classes -->
    </test>
    <test name="UI Tests">
        <!-- UI test classes -->
    </test>
</suite>
```

### Scenario Level Parallelism
```java
// TestRunner.java
@DataProvider(parallel = true)
public Object[][] scenarios() {
    return super.scenarios();
}
```

### Module Level Parallelism
```xml
<!-- Parent pom.xml -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```

### Execution Strategies
```bash
# Run tests in parallel (default)
mvn clean test

# Run tests sequentially
mvn test -Dparallel=none

# Run with custom thread count
mvn test -DthreadCount=8
```

## Advanced Features

### Data-Driven Testing
```java
// Example with Cucumber DataTables
@When("I create user with following details:")
public void createUserWithDetails(DataTable dataTable) {
    List<Map<String, String>> userData = dataTable.asMaps();
    for (Map<String, String> user : userData) {
        // Process each user data row
    }
}
```

### Custom Listeners
```java
// Custom TestNG Listener
public class CustomTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        // Custom test start logic
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot on failure
        ScreenshotUtils.captureScreenshot(driver, result.getName());
    }
}
```

### Retry Mechanism
```java
// Retry failed tests
@RetryAnalyzer = RetryFailedTests.class
public class RetryFailedTests implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3;
    
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
```

### Environment-Specific Configuration
```properties
# config.properties
env=prod

# Environment-specific properties
prod.base.url=https://api.prod.example.com
qa.base.url=https://api.qa.example.com
dev.base.url=https://api.dev.example.com
```

## Performance Optimization

### Test Execution Optimization
```bash
# Skip compilation for faster execution
mvn test -Dmaven.test.skip=false -Dmaven.compiler.skip=true

# Run tests in forked JVM for isolation
mvn test -DforkCount=2 -DreuseForks=true

# Enable parallel execution at method level
mvn test -Dparallel=methods -DthreadCount=4
```

### Memory Optimization
```xml
<!-- JVM Arguments -->
<argLine>-Xmx1024m -XX:+UseG1GC -Dfile.encoding=UTF-8</argLine>
```

## CI/CD Integration

### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/cucumber-reports',
                        reportFiles: 'cucumber.html',
                        reportName: 'Cucumber Report'
                    ])
                }
            }
        }
    }
}
```

### GitHub Actions Example
```yaml
name: Test Suite
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 8
      uses: actions/setup-java@v2
      with:
        java-version: '8'
    - name: Run tests
      run: mvn clean test -Dmaven.test.failure.ignore=true
    - name: Generate Allure Report
      run: allure generate target/allure-results --clean -o allure-report
```

## Best Practices

### Code Organization
- **Page Object Model**: Follow POM for UI tests
- **Factory Pattern**: Use factories for test data generation
- **Singleton Pattern**: For configuration and driver management
- **Builder Pattern**: For complex object creation
- **Consistent Naming**: Use TC1, TC2, etc. for test cases

### Test Data Management
- **External Configuration**: Use properties files for environment data
- **Data Factories**: Create reusable test data generators
- **Data-Driven Testing**: Use Cucumber DataTables for parameterization
- **Test Data Cleanup**: Implement proper cleanup mechanisms

### Error Handling
- **Custom Exceptions**: Create framework-specific exceptions
- **Retry Logic**: Implement retry for flaky tests
- **Logging**: Comprehensive logging for debugging
- **Screenshots**: Capture screenshots on test failures

### Reporting
- **Meaningful Descriptions**: Add clear test descriptions
- **Proper Assertions**: Use descriptive assertion messages
- **Test Categorization**: Use tags for test organization
- **Historical Data**: Maintain test execution history

## Troubleshooting

### Common Issues

#### 1. Browser Driver Issues
```bash
# Solution: Update WebDriverManager
# In pom.xml, ensure latest version:
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.8.0</version>
</dependency>
```

#### 2. ClassPath Issues
```bash
# Solution: Clean and rebuild
mvn clean install -U

# Verify dependencies
mvn dependency:tree
```

#### 3. Test Failures
```bash
# Check test logs
tail -f target/surefire-reports/*.log

# Run specific failing test
mvn test -Dtest=TestCaseName

# Enable debug logging
mvn test -X
```

#### 4. Memory Issues
```bash
# Increase JVM memory
export MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"

# Or in Maven
mvn test -Dmaven.surefire.jvmArgs="-Xmx1024m"
```

### Debug Mode
```bash
# Enable debug logging
mvn test -Dmaven.test.debug=true

# Run with specific log level
mvn test -Dlog.level=DEBUG

# Enable TestNG debug
mvn test -Dtestng.debug=true
```

## Performance Metrics

### Execution Time Benchmarks
- **API Tests**: ~10-15 seconds (parallel)
- **UI Tests**: ~40-60 seconds (parallel)
- **Full Suite**: ~1-2 minutes
- **Sequential**: ~3-5 minutes

### Resource Usage
- **Memory**: 512MB - 1GB per JVM
- **CPU**: 2-4 cores recommended
- **Disk**: ~100MB for reports

## Security Considerations

### Credential Management
```properties
# Use environment variables for sensitive data
api.username=${API_USERNAME}
api.password=${API_PASSWORD}
```

### SSL/TLS Configuration
```java
// Configure SSL for API calls
SSLContext sslContext = SSLContexts.createDefault();
HttpClient client = HttpClients.custom()
    .setSSLContext(sslContext)
    .build();
```

## Contributing

### Development Workflow
1. **Fork the repository**
2. **Create feature branch**: `git checkout -b feature/amazing-feature`
3. **Make changes** following coding standards
4. **Add tests** for new functionality
5. **Run full test suite**: `mvn clean test`
6. **Commit changes**: `git commit -m 'Add amazing feature'`
7. **Push to fork**: `git push origin feature/amazing-feature`
8. **Create Pull Request** with detailed description

### Code Standards
- **Java**: Follow Google Java Style Guide
- **Test Naming**: Use descriptive names (TC1_GetAllProductsSteps)
- **Comments**: Add meaningful comments for complex logic
- **Documentation**: Update README for new features

### Pull Request Guidelines
- **Clear Title**: Summarize changes
- **Detailed Description**: Explain what and why
- **Test Coverage**: Ensure all new code is tested
- **Documentation**: Update relevant documentation

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

- **Maintainer**: QAlasers Team
- **GitHub**: https://github.com/sailubandi/Precision-Unified-Automation-Framework
- **Issues**: https://github.com/sailubandi/Precision-Unified-Automation-Framework/issues

## Acknowledgments

- **[Selenium WebDriver](https://selenium.dev/)** - Browser automation
- **[RestAssured](https://rest-assured.io/)** - API testing framework
- **[Cucumber](https://cucumber.io/)** - BDD testing framework
- **[TestNG](https://testng.org/)** - Test execution framework
- **[Maven](https://maven.apache.org/)** - Build management tool
- **[Allure](https://qameta.io/allure/)** - Advanced reporting
- **[Extent Reports](https://www.extentreports.com/)** - HTML reporting

---

**Built with passion for quality automation testing** | **Version 1.0.0** | **Last Updated: 2026-04-10**
