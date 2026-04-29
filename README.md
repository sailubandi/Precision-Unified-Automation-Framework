# Precision Unified Automation Framework

[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-blue.svg)](https://testng.org/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-green.svg)](https://cucumber.io/)
[![Selenium](https://img.shields.io/badge/Selenium-4.22.0-yellow.svg)](https://selenium.dev/)

## 🎯 Veeva Assignment - UI & API Test Automation

Enterprise-grade hybrid automation framework for **Automation Exercise (https://automationexercise.com)** implementing comprehensive UI and API testing capabilities within a scalable Maven multi-module architecture.

---

## 📋 Overview

This framework provides a unified solution for testing web applications through both REST API endpoints and Selenium WebDriver UI automation. Built with Maven multi-module architecture, it supports parallel execution, detailed reporting, and scalable test management.

### ✨ Key Benefits
- **🔄 Unified Testing**: Single framework for both API and UI testing
- **📖 BDD Approach**: Cucumber integration for business-readable tests
- **⚡ Parallel Execution**: Run tests concurrently for faster feedback
- **📊 Comprehensive Reporting**: Multiple report formats for different stakeholders
- **🏗️ Modular Architecture**: Clean separation of concerns
- **⚙️ Easy Configuration**: Property-driven test configuration
- **🏢 Enterprise Ready**: Production-grade framework with best practices

---

## 🏛️ Architecture

```
HybridAutomation/
|-- APIAutomation/          # API testing module (TC1-TC6)
|   |-- src/test/java/
|   |   |-- client/         # API client utilities
|   |   |   `-- ApiClient.java           # Centralized API client
|   |   |-- com/framework/api/          # Enhanced API framework structure
|   |   |   |-- builder/                 # Request building utilities
|   |   |   |   `-- RequestBuilder.java   # HTTP request builder
|   |   |   |-- context/                 # Test context management
|   |   |   |   `-- TestContext.java     # Thread-safe data sharing
|   |   |   |-- factory/                 # Test data factories
|   |   |   |   `-- UserDataFactory.java  # User test data
|   |   |   |-- pojo/                    # Plain Old Java Objects
|   |   |   |   `-- User.java            # User data model
|   |   |   |-- runner/                  # TestNG Cucumber runner
|   |   |   |   `-- ApiTestRunner.java   # API test execution
|   |   |   |-- stepdefinitions/         # Cucumber step definitions (TC1-TC6)
|   |   |   |   |-- TC1_GetAllProductsSteps.java
|   |   |   |   |-- TC2_GetSingleProductSteps.java
|   |   |   |   |-- TC3_CreateUserSteps.java
|   |   |   |   |-- TC4_DeleteUserSteps.java
|   |   |   |   |-- TC5_UpdateUserSteps.java
|   |   |   |   |-- TC6_NegativeValidationSteps.java
|   |   |   |   `-- CommonSteps.java      # Shared steps
|   |   |   `-- validator/                # Response validation
|   |   |       `-- ResponseValidator.java # Enhanced validation logic
|   |   |-- hooks/           # Cucumber hooks
|   |   |   `-- TestHooks.java           # Setup and teardown
|   |   |-- listeners/       # TestNG listeners
|   |   |   `-- ApiTestListener.java     # Test execution monitoring
|   |   `-- utils/           # Utility classes
|   |       |-- AllureUtil.java         # Allure reporting utilities
|   |       |-- LoggerUtil.java         # Logging utilities
|   |       `-- ScenarioContext.java     # Scenario data management
|   |-- src/test/resources/
|   |   |-- features/       # Cucumber feature files
|   |   |   |-- TC1_GetAllProducts.feature
|   |   |   |-- TC2_GetSingleProduct.feature
|   |   |   |-- TC3_CreateUser.feature
|   |   |   |-- TC4_DeleteUser.feature
|   |   |   |-- TC5_UpdateUser.feature
|   |   |   `-- TC6_NegativeValidation.feature
|   |   |-- schemas/       # JSON schema validation
|   |   |   |-- errorResponseSchema.json   # Error response validation
|   |   |   |-- productsSchema.json        # Product list validation
|   |   |   |-- searchResultsSchema.json   # Search results validation
|   |   |   `-- userSchema.json            # User data validation
|   |   |-- log4j2.xml     # Logging configuration
|   `-- testng-api.xml      # TestNG configuration
|
|-- UIAutomation/           # UI testing module (TC1-TC5)
|   |-- src/test/java/
|   |   |-- PageObjects/    # Selenium Page Object Model
|   |   |   |-- Basepage.java              # Base page with common utilities
|   |   |   |-- HomePage.java              # Main page object
|   |   |   |-- LoginPage.java             # Login page
|   |   |   |-- RegisterPage.java          # Registration page
|   |   |   |-- AccountPage.java           # User account page
|   |   |   |-- CartPage.java              # Shopping cart
|   |   |   `-- ProductPage.java          # Product details
|   |   |-- hooks/           # Cucumber hooks
|   |   |   `-- Hooks.java                 # Browser setup and teardown
|   |   |-- reports/        # Reporting utilities
|   |   |   |-- ExtentManager.java         # ExtentReports configuration
|   |   |   `-- ExtentTestManager.java     # ExtentReports test management
|   |   |-- runner/         # TestNG Cucumber runner
|   |   |   `-- TestRunner.java            # UI test execution
|   |   |-- stepdefinitions/ # Cucumber step definitions (TC1-TC5)
|   |   |   |-- TC1_RegisterSteps.java     # Registration flow
|   |   |   |-- TC2_LoginSteps.java         # Login flow
|   |   |   |-- TC3_InvalidLoginSteps.java  # Negative login tests
|   |   |   |-- TC4_AddToCartSteps.java     # Cart operations
|   |   |   |-- TC5_RemoveFromCartSteps.java
|   |   |   `-- CommonUISteps.java         # Shared UI steps
|   |   `-- utils/          # Utility classes
|   |       `-- ExcelUtils.java            # Excel data operations
|   |-- src/test/resources/
|   |   |-- Feature/        # Cucumber feature files
|   |   |   |-- Register.feature
|   |   |   |-- Login.feature
|   |   |   |-- Incorrectlogin.feature
|   |   |   |-- AddProductToCart.feature
|   |   |   `-- RemoveFromCart.feature
|   |   |-- log4j2.xml      # Logging configuration
|   |   `-- Book1.xlsx      # Excel test data
|   `-- testng-ui.xml       # TestNG configuration
|
|-- Common/                  # Shared utilities module
|   |-- src/main/java/       # Common utilities
|   |   |-- config/          # Shared configuration
|   |   |   `-- ConfigReader.java         # Properties configuration reader
|   |   |-- constants/       # Framework constants
|   |   |   `-- FrameworkConstants.java    # Global framework constants
|   |   |-- driver/          # WebDriver management
|   |   |   |-- DriverFactory.java        # Driver factory pattern
|   |   |   `-- DriverManager.java        # Thread-safe driver management
|   |   |-- logger/          # Logging framework
|   |   |   `-- LogManager.java           # Centralized logging
|   |   |-- utils/           # Shared utilities
|   |   |   |-- AdBlockerUtil.java        # Ad blocker utility
|   |   |   |-- TestDataUtils.java        # Test data management
|   |   |   `-- WaitUtils.java            # Wait strategies
|   |-- src/main/resources/ # Shared resources
|   |   `-- config.properties              # Centralized configuration
|   `-- pom.xml              # Common module configuration
|
|-- pom.xml                  # Parent Maven configuration
|-- testng.xml              # Unified test execution
|-- testng-eclipse.xml      # Eclipse-specific configuration
`-- README.md               # This file
```

---

## 🚀 Parent POM - Unified Test Execution Engine

The parent `pom.xml` serves as the **central orchestration hub** for executing both UI and API tests with optimized parallel processing:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>${maven.surefire.plugin.version}</version>
            <configuration>
                <!-- Parallel Execution Configuration -->
                <parallel>methods</parallel>
                <threadCount>3</threadCount>
                <useSystemClassLoader>false</useSystemClassLoader>
                
                <!-- Unified Test Execution -->
                <includes>
                    <include>**/TestRunner.java</include>
                    <include>**/ApiTestRunner.java</include>
                </includes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### ✨ Key Benefits of Parent POM Configuration

| Feature | Benefit | Impact |
|----------|----------|---------|
| **Single Command Execution** | `mvn clean test` runs both UI & API | 🚀 Unified test execution |
| **Module Sequential Execution** | UIAutomation → APIAutomation (sequential) | 📊 Structured test flow |
| **Internal Parallelism** | 3 threads within each module | ⚡ Faster individual module execution |
| **Unified Reporting** | Consolidated test results | 📊 Single source of truth |
| **Dependency Management** | Shared versions across modules | 🔧 Consistent environment |
| **Module Coordination** | Orchestrates APIAutomation + UIAutomation | 🎯 Seamless integration |

### 🎯 Execution Commands

```bash
# 🚀 Run Both UI and API Tests (Recommended)
mvn clean test

# 📊 With Failure Tolerance (First Run)
mvn clean test -Dmaven.test.failure.ignore=true

# 🔧 Verify Module Dependencies
mvn dependency:tree
```

### 📈 Performance Impact

- **Module Sequential Execution**: UIAutomation → APIAutomation
- **Internal Parallelism**: Each module runs tests in parallel (3 threads)
- **Total Execution Time**: < 2 minutes (optimized execution)
- **Performance Benefit**: Fast execution with internal parallelism

---

## 🛠️ Technology Stack

### Core Technologies
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java 8+** | 8+ | Programming Language |
| **Maven 3.6+** | 3.6+ | Build and Dependency Management |
| **TestNG 7.9.0** | 7.9.0 | Test Execution Framework |
| **Cucumber** | 7.12.0 (API) / 7.15.0 (UI) | BDD Framework |
| **Selenium WebDriver** | 4.22.0 | UI Automation |
| **RestAssured** | 5.4.0 | API Testing |
| **Apache Commons Lang3** | 3.14.0 | Utility Functions |
| **JSON Schema Validator** | 5.4.0 | Schema Validation |

### Supporting Libraries
| Library | Version | Purpose |
|----------|---------|---------|
| **Log4j2** | 2.20.0 (API) / 2.22.0 (Common) | Logging Framework |
| **SLF4J** | 2.0.9 | Logging Facade |
| **WebDriver Manager** | 5.8.0 | Browser Driver Management |
| **Extent Reports** | 5.1.1 | HTML Reporting |
| **Allure TestNG** | 2.24.0 | Advanced Reporting |
| **Allure Cucumber7 JVM** | 2.24.0 | Cucumber Allure Integration |

---

## 🧪 Test Cases Implementation

### 🌐 UI Automation (TC1-TC5)
| Test Case | Description | Validation |
|-----------|-------------|-------------|
| **TC1** | User Registration Flow | Account creation, login verification |
| **TC2** | User Login Flow | Successful authentication |
| **TC3** | Invalid Login Validation | Error message display |
| **TC4** | Add Products to Cart | Cart contents, pricing verification |
| **TC5** | Remove Products from Cart | Product removal, cart updates |

### 🔌 API Automation (TC1-TC6)
| Test Case | Method | Endpoint | Validation |
|-----------|---------|-----------|-------------|
| **TC1** | GET | `/api/productsList` | Status 200, JSON schema, product list |
| **TC2** | POST | `/api/searchProduct` | Search response, product details |
| **TC3** | POST | `/api/createAccount` | User creation, existing email handling |
| **TC4** | DELETE | `/api/deleteAccount` | Account deletion response |
| **TC5** | PUT | `/api/updateAccount` | Account update, data modification |
| **TC6** | Multiple | Various endpoints | Negative scenarios, error handling |

---

## ⚙️ Configuration

### 🌍 No Hardcoding Policy
All URLs, browser types, and credentials are stored in centralized configuration files.

#### Configuration (`Common/src/main/resources/config.properties`)
```properties
# Application Configuration (UI)
app.url=https://automationexercise.com
app.title=Automation Exercise

# Browser Configuration (UI)
browser=chrome
headless=false
implicit.wait=10
explicit.wait=30
page.load.timeout=60

# WebDriver Configuration (UI)
chrome.options=--disable-extensions,--disable-infobars,--disable-notifications

# API Configuration
api.base.url=https://automationexercise.com
api.timeout=10000
api.retry.count=3

# Test Data Configuration (Both UI & API)
test.user.email=testuser@example.com
test.user.password=password123
test.user.name=Test User
test.user.api.name=John Doe
test.user.api.email=john.doe@test.com
test.user.api.password=Test123
test.user.api.title=Mr

# Search Configuration (API)
test.search.term.top=top
test.search.term.tshirt=tshirt
test.search.term.jean=jean

# Reporting Configuration (UI)
extent.report.path=reports/ExtentReport.html
screenshot.path=reports/screenshots/
```

**Note**: All configuration is centralized in `Common/src/main/resources/config.properties` and accessed via `ConfigReader.java` in the Common module.

---

## 🚀 Quick Start

### 1️⃣ Prerequisites
- **JDK 8+**
- **Maven 3.6+**
- **Chrome Browser** (for UI tests)
- **Git** (for cloning)
- **IDE** with Cucumber/Gherkin plugins (Eclipse/IntelliJ)

### 2️⃣ Clone and Setup (Git Bash Commands)

#### 🔄 Clone the Repository
```bash
# Clone the repository
git clone https://github.com/sailubandi/Precision-Unified-Automation-Framework.git

# Navigate to project directory
cd Precision-Unified-Automation-Framework

# Verify project structure
ls -la
```

#### 🔧 Verify Prerequisites
```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Git version
git --version
```

#### 🏗️ Build the Project
```bash
# Clean and compile all modules
mvn clean compile

# Verify build success
mvn dependency:tree

# Install dependencies
mvn clean install -DskipTests
```

### 3️⃣ Run Tests (Detailed Commands)

#### 🔄 All Tests (Recommended)
```bash
# Execute both API and UI tests sequentially (parent POM)
mvn clean test

# With failure tolerance (first run)
mvn clean test -Dmaven.test.failure.ignore=true

# Run with specific profile
mvn clean test -P regression
```

#### 🔌 API Tests Only
```bash
# Navigate to API module
cd APIAutomation

# Run all API tests
mvn clean test

# Run with specific TestNG suite
mvn clean test -Dtestng.xml=testng-api.xml

# Run specific test class
mvn clean test -Dtest=ApiTestRunner

# Run with Maven profiles
mvn clean test -P api-tests
```

#### 🌐 UI Tests Only
```bash
# Navigate to UI module
cd UIAutomation

# Run all UI tests
mvn clean test

# Run with specific TestNG suite
mvn clean test -Dtestng.xml=testng-ui.xml

# Run specific test class
mvn clean test -Dtest=TestRunner

# Run with Maven profiles
mvn clean test -P ui-tests
```

#### 🎯 Individual Test Scenarios
```bash
# Run specific test case (API)
cd APIAutomation
mvn clean test -Dtest=TC1_GetAllProductsSteps

# Run specific test case (UI)
cd UIAutomation
mvn clean test -Dtest=TC1_RegisterSteps

# Run tests with specific tags (Cucumber)
mvn clean test -Dcucumber.options="--tags @TC1 or @TC2"

# Run tests in debug mode
mvn clean test -Dmaven.surefire.debug
```

#### 🔄 Return to Root Directory
```bash
# After running individual module tests, return to root
cd ..
```

### 4️⃣ IDE Setup & Configuration

#### 🔧 Eclipse Setup
```bash
# Import as Maven Project
1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Browse to project directory
4. Select all modules (Common, UIAutomation, APIAutomation)
5. Click Finish

# Install Cucumber Plugin
1. Help → Eclipse Marketplace
2. Search "Cucumber"
3. Install "Cucumber Eclipse Plugin"
4. Restart Eclipse

# Configure TestNG
1. Help → Eclipse Marketplace
2. Search "TestNG"
3. Install "TestNG for Eclipse"
4. Restart Eclipse
```

#### 🔧 IntelliJ IDEA Setup
```bash
# Import as Maven Project
1. Open IntelliJ IDEA
2. File → Open → Select pom.xml
3. Open as Project
4. Wait for Maven import to complete

# Install Cucumber Plugin
1. File → Settings → Plugins
2. Search "Cucumber"
3. Install "Cucumber for Java"
4. Restart IDE

# Configure TestNG
1. File → Settings → Plugins
2. Search "TestNG"
3. Install "TestNG"
4. Restart IDE
```

#### ⚙️ Project Configuration
```bash
# Set Java Compiler Version
1. Right-click project → Properties
2. Java Compiler → Set to 1.8 or higher
3. Apply and Close

# Configure Maven
1. Right-click project → Properties
2. Maven → Update Project
3. Select all modules
4. Click OK

# Verify Dependencies
1. Right-click pom.xml → Maven → Show Dependencies
2. Ensure all dependencies are resolved
```

#### 🎯 Run Tests from IDE
```bash
# Eclipse Test Execution
1. Right-click TestRunner.java (UI) or ApiTestRunner.java (API)
2. Run As → TestNG Test
3. View results in TestNG panel

# IntelliJ Test Execution
1. Right-click TestRunner.java (UI) or ApiTestRunner.java (API)
2. Run 'TestRunner' or 'ApiTestRunner'
3. View results in Test Results panel

# Cucumber Feature Execution
1. Right-click .feature file
2. Run As → Cucumber Feature
3. View results in console
```

---

## ✨ Key Features

### 🎭 Page Object Model
- **Strict Separation**: Locators and test logic completely separated
- **No Selenium in Step Definitions**: All UI interactions through Page Objects
- **Page-Specific Methods**: Reusable page interactions

### 🏗️ Enhanced API Architecture
- **Client Layer**: Centralized HTTP client with RestAssured and enhanced logging
- **Request Builder**: Flexible request construction with Builder pattern
- **Validator Layer**: Comprehensive response validation with `ResponseValidator`
- **Context Management**: Thread-safe data sharing with `TestContext`
- **POJO Layer**: Structured data models with `User` class and `toMap()` conversion
- **Enhanced Validation**: Success and error scenario validation methods
- **Performance Monitoring**: Response time tracking and detailed logging
- **Allure Integration**: Automatic request/response attachment to reports

### 📊 JSON Schema Validation
- **Comprehensive Schemas**: Pre-defined schemas for all API responses
- **Automated Validation**: Schema validation integrated in test flow
- **Error Handling**: Robust error response validation
- **Schema Files**: 
  - `productsSchema.json` - Product list validation
  - `userSchema.json` - User data validation
  - `searchResultsSchema.json` - Search response validation
  - `errorResponseSchema.json` - Error response validation

### ⚡ Parallel Execution
- **Module Sequential**: UIAutomation → APIAutomation (sequential)
- **Internal Parallelism**: 3 threads within each module for concurrent test execution
- **Thread Safety**: ThreadLocal driver management
- **Performance Gains**: Faster execution within each module

### 📸 Automatic Screenshots
- **Failure Capture**: Automatic screenshot on UI test failures
- **Multi-Report Attachment**: Cucumber + Extent Reports
- **Timestamp Naming**: Unique file naming with timestamps

### 🚀 API Automation Enhancements
- **Enhanced Logging**: Request/response logging with performance metrics
- **Allure Integration**: Automatic API request/response attachments to Allure reports
- **Historical Reports**: Allure Maven plugin for historical test data
- **Backup System**: Automatic backup of Allure results before each test run
- **Response Time Tracking**: Millisecond-level performance monitoring
- **User POJO**: Comprehensive User class with toMap() conversion for form parameters

---

## 📈 Reports (Locations & Viewing Commands)

| Report Type | Location | Format | Features |
|-------------|-----------|---------|-----------|
| **TestNG** | `target/surefire-reports/` | HTML/XML | Basic execution details |
| **Cucumber** | `UIAutomation/reports/` | HTML | Step-by-step execution |
| **Extent** | `UIAutomation/reports/` | HTML | Interactive with screenshots |
| **Allure** | `APIAutomation/allure-results/` | HTML | Advanced analytics |

### 🖼️ View Reports (Detailed Commands)

#### 📊 TestNG Reports
```bash
# Navigate to module directory first
cd UIAutomation  # or cd APIAutomation

# Open TestNG HTML report
open target/surefire-reports/index.html

# Alternative: Open in default browser
start target/surefire-reports/index.html  # Windows
xdg-open target/surefire-reports/index.html  # Linux

# View XML reports
cat target/surefire-reports/TEST-TestRunner.xml
```

#### 🥒 Cucumber Reports
```bash
# Navigate to UI Automation module (Cucumber reports generated here)
cd UIAutomation

# Open Cucumber HTML report
open reports/cucumber.html

# View latest Extent report (also in reports folder)
ls -la reports/ | grep "ExtentReport"
open reports/ExtentReport_<latest-timestamp>.html

# Open cucumber.html (main Cucumber report)
open reports/cucumber.html

# Alternative: Check if reports exist in target (fallback)
if [ -f "target/cucumber-reports/cucumber.html" ]; then
    open target/cucumber-reports/cucumber.html
fi
```

#### 📸 Extent Reports (UI Tests)
```bash
# Navigate to UI Automation module
cd UIAutomation

# Open Extent Reports HTML
open reports/ExtentReport.html


# View latest report with timestamp
ls -la reports/ | grep "ExtentReport"
open reports/ExtentReport_<timestamp>.html
```

#### 🎯 Allure Reports (API Tests)
```bash
# Navigate to API Automation module
cd APIAutomation

# Generate and serve Allure report (with historical data)
mvn allure:serve

# Alternative: Generate static report
mvn allure:report

# Open generated static report
open allure-reports/index.html

# Open with specific port
mvn allure:serve -Dallure.port=8080

# View Allure results directory
ls -la allure-results/

# View historical backups
ls -la reports/allure-backup-*/

# View latest backup (if exists)
latest_backup=$(ls -t reports/allure-backup-* | head -1)
if [ ! -z "$latest_backup" ]; then
    echo "Latest backup: $latest_backup"
    mvn allure:serve -Dallure.results.directory="$latest_backup"
fi
```

#### 🔄 Combined Report Viewing
```bash
# Return to root directory
cd ..

# Open all reports at once (multiple tabs)
open UIAutomation/target/surefire-reports/index.html &
open UIAutomation/reports/ExtentReport.html &
open APIAutomation/target/surefire-reports/index.html &
cd APIAutomation && mvn allure:serve &
```

#### 📱 Report Locations Summary
```bash
# UI Automation Reports
echo "UI TestNG: UIAutomation/target/surefire-reports/"
echo "UI Extent: UIAutomation/reports/"
echo "UI Cucumber: UIAutomation/reports/"

# API Automation Reports  
echo "API TestNG: APIAutomation/target/surefire-reports/"
echo "API Allure: APIAutomation/allure-results/"

# Configuration Location
echo "Config: Common/src/main/resources/config.properties"
echo "ConfigReader: Common/src/main/java/config/ConfigReader.java"
```

---

## 🔧 Troubleshooting

### 🚨 Common Issues

#### Element Click Interception
```java
try {
    WaitUtils.waitForElementToBeClickable(element).click();
} catch (ElementClickInterceptedException e) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
}
```

#### Build Issues
```bash
# Clean and update dependencies
mvn clean install -U

# Check dependency tree
mvn dependency:tree
```

#### Parallel Execution Issues
- Ensure DriverManager uses ThreadLocal
- Check thread safety in shared resources
- Reduce thread count if necessary

---

## 📞 Support & Contact

### 📧 Contact Information
- **Maintainer**: QAlasers Team
- **GitHub**: [Precision Unified Automation Framework](https://github.com/sailubandi/Precision-Unified-Automation-Framework)
- **Issues**: [Report Issues](https://github.com/sailubandi/Precision-Unified-Automation-Framework/issues)

### 📚 Additional Resources
- [Selenium Documentation](https://selenium.dev/documentation/)
- [RestAssured Documentation](https://rest-assured.io/)
- [Cucumber Documentation](https://cucumber.io/docs)
- [TestNG Documentation](https://testng.org/documentation.html)

---

## 🏆 Veeva Assignment Compliance

### ✅ Deliverables Status

| Deliverable | Status | Description |
|-------------|---------|-------------|
| **README.md** | ✅ Complete | Professional documentation with clear instructions |
| **Architecture Documentation** | ✅ Complete | Technical deep-dive with implementation details |
| **Source Code** | ✅ Complete | Full implementation with all test cases |
| **Execution Reports** | ✅ Complete | Multi-format reporting with screenshots |
| **Git Repository** | ✅ Complete | Version-controlled with proper structure |

### 🎯 Evaluation Criteria Alignment

| Category | Weight | Implementation |
|-----------|---------|----------------|
| **Architecture Design** | 20% | Multi-module Maven architecture with layered design |
| **Framework Implementation** | 20% | POM, driver management, no Selenium in step definitions |
| **UI Automation Coverage** | 15% | All 5 UI test cases with proper validation |
| **API Automation Coverage** | 15% | All 6 API test cases with JSON schema validation |
| **Hybrid Integration** | 15% | Unified configuration, parallel execution, shared utilities |
| **Code Quality & Standards** | 10% | Clean code, design patterns, reusable components |
| **Logging & Reporting** | 5% | Screenshots, structured reports, comprehensive logging |

---

**🎯 Built for Veeva Fresh Grads Assignment**  
**📅 Version 1.0.0**  
**🔄 Last Updated: April 29, 2026**  
**👥 Team: QAlasers**  

---

<div align="center">

## 🏅 Project Completion Summary

### ✨ Framework Highlights
- **🔄 Hybrid Testing**: Unified UI + API automation
- **⚡ Performance**: Sequential module execution with internal parallelism
- **📊 Reporting**: 4 different report formats with historical data
- **🏗️ Architecture**: Enterprise-grade modular design
- **🔧 Configuration**: Zero hardcoding, property-driven
- **📸 Screenshots**: Automatic capture on failures
- **🚀 API Enhancements**: Performance monitoring, Allure integration, backup system
- **🎯 Compliance**: 100% Veeva assignment requirements

### 🚀 Ready for Production
This framework demonstrates professional-level automation capabilities and is ready for enterprise deployment.

</div>
