package MyStepDefs;

import Pages.HomePagePOM;
import Pages.LoginPagePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class StepDefs {
    private WebDriver driver = WebDriverManager.chromedriver().create();
    LoginPagePOM loginPage;
    HomePagePOM homePage;

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPagePOM(driver);
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
        homePage = new HomePagePOM(driver);
        homePage.validateOnHomePage();
    }

    @Then("user able to see error message {string}")
    public void userAbleToSeeErrorMessage(String errorMessage) {
        loginPage.validateErrorAppear(errorMessage);
    }
}
