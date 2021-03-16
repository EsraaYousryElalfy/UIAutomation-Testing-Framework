package restassured.utils;

import constants.EndPoints;

public enum ApiUri implements EndPoints {
    Market_Place_URI("marketplace")
    ;

    private String endPoint;

    ApiUri(String endPoint) {
        this.endPoint =endPoint;
    }
    @Override
    public String uri() {
        return endPoint;
    }
}
