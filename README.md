# Insider Test Automation Framework

A comprehensive test automation framework for testing the Insider website using Java, Selenium WebDriver, and TestNG with Page Object Model (POM) design pattern.

## Project Structure

```
insider-test-automation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── insider/
│   │               └── pages/
│   │                   ├── BasePage.java
│   │                   ├── HomePage.java
│   │                   ├── CareersPage.java
│   │                   └── QAJobsPage.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── insider/
│                   └── tests/
│                       ├── BaseTest.java
│                       └── InsiderTest.java
│
├── pom.xml
├── testng.xml
└── README.md
```

## Technologies Used

- **Java 11**: Programming language
- **Selenium WebDriver 4.15.0**: Browser automation
- **TestNG 7.8.0**: Testing framework
- **WebDriverManager 5.6.2**: Automatic driver management
- **Maven**: Build and dependency management

## Features

- **Page Object Model (POM)**: Clean separation of test logic and page elements
- **Optimized Locators**: CSS and XPath selectors for reliable element identification
- **Wait Strategies**: Explicit waits for stable test execution
- **Reusable Components**: Base classes for common functionality
- **Clear Assertions**: TestNG assertions for test validation
- **Clean Code**: Well-structured, readable, and maintainable code

## Test Cases Covered

1. **Test Home Page**: Verify Insider home page opens successfully
2. **Test Careers Page Blocks**: Validate Locations, Teams, and Life at Insider blocks
3. **Test QA Jobs Filtering**: Filter jobs by location and department
4. **Test Job Details**: Verify all jobs meet filtering criteria
5. **Test View Role Redirection**: Confirm redirection to Lever application page

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome browser installed
- Internet connection

## Installation

1. Clone the repository or create a new Maven project
2. Copy all the files to appropriate directories
3. Run Maven to download dependencies:

```bash
mvn clean install -DskipTests
```

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run via TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run specific test class:
```bash
mvn test -Dtest=InsiderTest
```

### Run specific test method:
```bash
mvn test -Dtest=InsiderTest#testInsiderHomePageIsOpened
```

## Page Objects Explanation

### BasePage.java
Base class containing common methods used across all page objects:
- Element wait strategies
- Click actions
- Scroll functionality
- Window handling

### HomePage.java
Handles interactions with the Insider home page:
- Opening home page
- Cookie consent handling
- Navigation to Careers section

### CareersPage.java
Manages Careers page elements:
- Locations block verification
- Teams block verification
- Life at Insider block verification

### QAJobsPage.java
Handles Quality Assurance jobs page:
- Job filtering by location and department
- Job list validation
- Job details extraction
- View Role button interaction

## Key Design Decisions

### Locator Strategy
- **CSS Selectors**: Used for ID and class-based elements (faster)
- **XPath**: Used for text-based navigation and complex hierarchies
- All locators are optimized for performance and maintainability

### Wait Strategy
- Explicit waits with 15-second timeout
- Custom wait methods in BasePage
- Page load waits for dynamic content

### Test Independence
- Each test can run independently
- Tests are ordered using priority for logical flow
- Fresh browser instance for each test method

## Troubleshooting

### Common Issues

**Chrome driver not found:**
- WebDriverManager automatically downloads the driver
- Ensure internet connection is available

**Element not found:**
- Increase wait timeout in BasePage if needed
- Website structure might have changed

**Tests running slowly:**
- Network latency can affect performance
- Consider running tests during off-peak hours

## Best Practices Implemented

- Single Responsibility Principle for page objects
- DRY (Don't Repeat Yourself) principle
- Meaningful variable and method names
- Proper exception handling
- Thread-safe driver management
- Clean test data separation

## Future Enhancements

- Add configuration file for test data
- Implement parallel test execution
- Add screenshot capture on failure
- Integrate with CI/CD pipeline
- Add logging framework
- Implement data-driven testing
- Add API testing capabilities

## Contributing

1. Follow the existing code style
2. Add comments for complex logic
3. Update tests when adding new features
4. Ensure all tests pass before committing

## License

This project is created for testing purposes.

## Contact

For questions or issues, please refer to the project documentation.