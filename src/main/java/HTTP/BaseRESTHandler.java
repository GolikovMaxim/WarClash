package HTTP;

import com.sun.net.httpserver.HttpExchange;

public class BaseRESTHandler implements RESTHandler {
    @Override
    public RESTResponse HandleGET(HttpExchange exchange) {
        return new RESTResponse(405, "Method not Allowed");
    }

    @Override
    public RESTResponse HandlePOST(HttpExchange exchange) {
        return new RESTResponse(405, "Method not Allowed");
    }

    @Override
    public RESTResponse HandlePUT(HttpExchange exchange) {
        return new RESTResponse(405, "Method not Allowed");
    }

    @Override
    public RESTResponse HandlePATCH(HttpExchange exchange) {
        return new RESTResponse(405, "Method not Allowed");
    }

    @Override
    public RESTResponse HandleDELETE(HttpExchange exchange) {
        return new RESTResponse(405, "Method not Allowed");
    }
}
