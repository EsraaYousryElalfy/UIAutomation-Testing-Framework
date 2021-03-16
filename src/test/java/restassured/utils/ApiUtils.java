package restassured.utils;

import constants.EndPoints;
import constants.SystemProperties;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiUtils {

    private final RequestSpecification request;

    public ApiUtils(ContentType contentType, String token) {
        request = createRequest(new Header("Authorization", token), contentType);
    }

    private final static String API_SERVER = SystemProperties.getRestAssuredUrl();

    private RequestSpecification createRequest(Header header, ContentType contentType) {
        return given().header(header).accept(contentType);
    }

    public Response sendGetRequestWithoutFiltration(String restURL) {
        return request.get(API_SERVER + restURL);
    }


    public Response sendPostRequestWithQueryParameters(String restURL, Map<String, Object> parameters) {
        parameters.forEach(request::param);
        return request.get(API_SERVER + restURL);
    }

    public void assertResponseSucceeded(Response response) {
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
}
