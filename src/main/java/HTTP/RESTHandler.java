package HTTP;

import com.sun.net.httpserver.HttpExchange;

public interface RESTHandler {
    RESTResponse HandleGET(HttpExchange exchange);
    RESTResponse HandlePOST(HttpExchange exchange);
    RESTResponse HandlePUT(HttpExchange exchange);
    RESTResponse HandlePATCH(HttpExchange exchange);
    RESTResponse HandleDELETE(HttpExchange exchange);
}
