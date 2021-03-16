package cucumber.stepDef;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.testng.Assert;
import restassured.api.MarketPlace;
import selenium.pages.*;

public class ApiStepDef {

    @Given("send get request")
    public void sendGetRequest() {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.getResponseWithoutFilteration();
        marketPlace.getResponseWithFiltration("30","30");

    }
}
