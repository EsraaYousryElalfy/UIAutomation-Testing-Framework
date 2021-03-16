package restassured.api;

import io.restassured.response.Response;
import restassured.utils.ApiUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static restassured.utils.ApiUri.Market_Place_URI;

public class MarketPlace extends ApiUtils {
    public MarketPlace() {
        super(JSON, "token");
    }

}
