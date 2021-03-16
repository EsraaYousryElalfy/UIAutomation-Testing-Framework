package cucumber.stepDef;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import selenium.pages.*;

import java.util.List;
import java.util.Map;

public class UIAutomationStepDef {
    public static String customerID;
    public static String accountNumber;
    private final static String firstName = new Faker().name().firstName();
    private final static String lastName = new Faker().name().lastName();
    private final static String postCode = new Faker().numerify("#####");


    @Given("The User in the protector anglarjs")
    public void theUserInTheHomePage() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.getPageUrl().contains("protractor-angularjs-practice-website.html"), "The protector anglarjs page not opened");
    }

    @And("click on Banking link under AngularJS Elements Lis")
    public void clickOnBankingLinkUnderAngularJSElementsLis() {
        HomePage homePage = new HomePage();
        homePage.openBankMainPage();

    }

    @When("user Clicks on Bank Manager Login")
    public void clickOnBankManagerLogin() {
        XYZBankPage xyzBankPage = new XYZBankPage();
        xyzBankPage.openBankManagerLoginPage();
    }

    @And("user Clicks on Add Customer")
    public void clickOnAddCustomer() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openAddCustomerPage();
    }

    @And("Fill all customer fields")
    public void fillAllCustomerFields() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.enterFirstName(firstName);
        addCustomerPage.enterLastName(lastName);
        addCustomerPage.enterPostCode(postCode);
    }


    @And("user clicks on Add Customer Button")
    public void userClicksOnAddCustomerButton() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.clickAddCustomerButton();
    }

    @And("Get the customer id from the alert")
    public void getTheCustomerIdFromTheAlertThenCloseTheAlert() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        customerID = addCustomerPage.getCustomerId();
    }

    @And("user close the alert")
    public void userCloseTheAlert() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.dismissTheAlert();
    }

    @And("user clicks on Customers Tab")
    public void clickOnCustomersTab() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openCustomersPage();
    }

    @Then("the user displayed in the same order abd the data displayed correctly")
    public void theUserOrderInTheListIsTheSameAsTheValueRetrievedFromTheAlert() {
        CustomersPage customersPage = new CustomersPage();
        Map<String, String> customerData = customersPage.getAllCustomers(customerID);
        Assert.assertTrue(customerData.containsValue(firstName), "First name not displayed for added user");
        Assert.assertTrue(customerData.containsValue(lastName), "last name not displayed for added user");
        Assert.assertTrue(customerData.containsValue(postCode), "post code not displayed for added user");
    }


    @Given("The User in XYZ Bank Page and click on open account")
    public void theUserInXYZBankPageAndClickOnOpenAccount() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openAccountPage();
    }

    @When("Select the customer entered in the ScenarioOne and select a random currency and Submit account by clicking on Process Button")
    public void selectTheCustomerEnteredInTheScenarioOneAndSelectARandomCurrencyAndSubmitAccountByClickingOnProcessButton() {
        OpenAccountPage openAccountPage = new OpenAccountPage();
        openAccountPage.selectCustomer(firstName + " " + lastName);
        openAccountPage.selectCurrency();
        openAccountPage.clickOnProcess();
    }

    @And("Get the Account Number from the Alert then close the alert")
    public void getTheAccountNumberFromTheAlertThenCloseTheAlert() {
        OpenAccountPage openAccountPage = new OpenAccountPage();
        accountNumber = openAccountPage.getNumberFromAlert();
        openAccountPage.dismissTheAlert();
    }

    @And("Click on Customers")
    public void clickOnCustomers() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openCustomersPage();
    }

    @Then("the Account Number retrieved from the alert is displayed in its cell for the created customer")
    public void assertThatTheAccountNumberRetrievedFromTheAlertIsDisplayedInItsCellForTheCreatedCustomer() {
        CustomersPage customersPage = new CustomersPage();
        Map<String, String> customerData = customersPage.getAllCustomers(customerID);
        Assert.assertTrue(customerData.containsValue(accountNumber), "Account number not displayed correctly");
    }

    @Given("The User in XYZ Bank Customers Tab")
    public void theUserInXYZBankCustomersTab() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openCustomersPage();
    }

    @When("Click on Delete for the created customer in the scenarioOne")
    public void clickOnDeleteForTheCreatedCustomerInTheScenarioOne() {
        CustomersPage customersPage = new CustomersPage();
        customersPage.searchAboutCustomer(firstName);
        customersPage.deleteAccount();
    }

    @Then("the customer is no longer found in the customers table")
    public void assertThatTheCustomerIsNoLongerFoundInTheCustomersTable() {
        CustomersPage customersPage = new CustomersPage();
        customersPage.searchAboutCustomer("");
        List<Map<String,String>>  customers =customersPage.getAllCustomers();
        Assert.assertTrue(customers.stream().noneMatch(map->map.containsValue(firstName)), "The user not deleted");
        Assert.assertTrue(customers.stream().noneMatch(map->map.containsValue(lastName)), "The user not deleted");
        Assert.assertTrue(customers.stream().noneMatch(map->map.containsValue(postCode)), "The user not deleted");
    }

    @And("user Clicks on open account")
    public void userClicksOnOpenAccount() {
        BankManagerPage bankManagerPage = new BankManagerPage();
        bankManagerPage.openAccountPage();
    }
}
