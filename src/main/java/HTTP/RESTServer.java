package HTTP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RESTServer {
    public static final String GET = "GET", POST = "POST", PUT = "PUT", PATCH = "PATCH", DELETE = "DELETE";

    private class RESTHttpHandler implements HttpHandler {
        private Map<String, Function<HttpExchange, RESTResponse>> handlers;

        RESTHttpHandler(RESTHandler restHandler) {
            handlers = new HashMap<>();
            handlers.put(GET, restHandler::HandleGET);
            handlers.put(POST, restHandler::HandlePOST);
            handlers.put(PUT, restHandler::HandlePUT);
            handlers.put(PATCH, restHandler::HandlePATCH);
            handlers.put(DELETE, restHandler::HandleDELETE);
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            var handler = handlers.get(exchange.getRequestMethod());
            if(handler != null) {
                RESTResponse response = handler.apply(exchange);
                exchange.sendResponseHeaders(response.getCode(), response.getResponseBody().getBytes().length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getResponseBody().getBytes());
                outputStream.flush();
            } else {
                //CHANGE
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        }
    }

    private HttpServer httpServer;

    public RESTServer(int port) throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.setExecutor(null);
    }

    public void start() {
        httpServer.start();
    }

    public void addEndPoint(String endPoint, RESTHandler restHandler) {
        httpServer.createContext(endPoint, new RESTHttpHandler(restHandler));
    }
}
