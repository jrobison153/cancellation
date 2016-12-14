package temp;

import java.util.Map;

/**
 * Created by justin on 12/8/16.
 */
public class ApiGatewayProxyResponse {

    private String statusCode;

    private Map<String, String> headers;

    private String body;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
