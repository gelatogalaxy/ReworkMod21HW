package MyStepDefs;

import Pages.HomePagePOM;
import Pages.LoginPagePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StepDefs {
    private WebDriver driver;
    LoginPagePOM loginPage;
    HomePagePOM homePage;

    private WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            // CI runners have no display, so Chrome must run headless
            if (System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            String chromeBinary = System.getenv("CHROME_BIN") != null
                    ? System.getenv("CHROME_BIN")
                    : System.getenv("CHROME_PATH");
            if (chromeBinary != null && !chromeBinary.isEmpty()) {
                options.setBinary(chromeBinary);
            }
            driver = WebDriverManager.chromedriver().capabilities(options).create();
        }
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPagePOM(getDriver());
        loginPage.goToLoginPage();
    }

    @And("user input username with {string}")
    public void userInputUsernameWith(String username) {
        loginPage.inputUsername(username);
    }

    @And("user input empty username")
    public void userInputEmptyUsername() {
        // no action
    }

    @And("user input password with {string}")
    public void userInputPasswordWith(String password) {
        loginPage.inputPassword(password);
    }

    @When("user click login button")
    public void userClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user is on homepage")
    public void userIsOnHomepage() {
        homePage = new HomePagePOM(getDriver());
        homePage.validateOnHomePage();
    }

    @Then("user able to see error message {string}")
    public void userAbleToSeeErrorMessage(String errorMessage) {
        loginPage.validateErrorAppear(errorMessage);
    }
}
