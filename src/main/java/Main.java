import HTTP.BaseRESTHandler;
import HTTP.RESTResponse;
import HTTP.RESTServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RESTServer restServer = new RESTServer(8000);

        restServer.addEndPoint("/bek", new BaseRESTHandler() {
            @Override
            public RESTResponse HandleGET(HttpExchange exchange) {
                return new RESTResponse(200, "GET BEK");
            }

            @Override
            public RESTResponse HandlePOST(HttpExchange exchange) {
                return new RESTResponse(200, "POST BEK");
            }

            @Override
            public RESTResponse HandlePUT(HttpExchange exchange) {
                return new RESTResponse(200, "PUT BEK");
            }

            @Override
            public RESTResponse HandlePATCH(HttpExchange exchange) {
                return new RESTResponse(200, "PATCH BEK");
            }
        });

        restServer.start();
    }
}
