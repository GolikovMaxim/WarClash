package HTTP;

import com.sun.net.httpserver.HttpExchange;

public class BaseRESTHandler implements RESTHandler {
    @Override
    public RESTResponse handleGET(HttpExchange exchange) {
        return RESTHandler.BAD_METHOD;
    }

    @Override
    public RESTResponse handlePOST(HttpExchange exchange) {
        return RESTHandler.BAD_METHOD;
    }

    @Override
    public RESTResponse handlePUT(HttpExchange exchange) {
        return RESTHandler.BAD_METHOD;
    }

    @Override
    public RESTResponse handlePATCH(HttpExchange exchange) {
        return RESTHandler.BAD_METHOD;
    }

    @Override
    public RESTResponse handleDELETE(HttpExchange exchange) {
        return RESTHandler.BAD_METHOD;
    }
}
