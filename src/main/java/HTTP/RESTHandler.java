package HTTP;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpURLConnection;

public interface RESTHandler {
    RESTResponse BAD_METHOD = new RESTResponse(HttpURLConnection.HTTP_BAD_METHOD, "Method not Allowed");

    RESTResponse handleGET(HttpExchange exchange);
    RESTResponse handlePOST(HttpExchange exchange);
    RESTResponse handlePUT(HttpExchange exchange);
    RESTResponse handlePATCH(HttpExchange exchange);
    RESTResponse handleDELETE(HttpExchange exchange);
}
