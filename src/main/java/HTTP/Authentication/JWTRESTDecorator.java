package HTTP.Authentication;

import HTTP.RESTHandler;
import HTTP.RESTResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sun.net.httpserver.HttpExchange;

import java.net.HttpURLConnection;
import java.util.function.Function;

public class JWTRESTDecorator implements RESTHandler {
    private static final String HEADER_AUTHENTICATION = "Authorization";
    private static final RESTResponse AUTHENTICATION_ERROR_RESPONSE = new RESTResponse(HttpURLConnection.HTTP_UNAUTHORIZED, "Authentication error");

    public static final String SECRET_KEY = "secret_for_WarClash";

    private RESTHandler restHandler;
    private JWTVerifier jwtVerifier;

    public JWTRESTDecorator(RESTHandler restHandler) {
        this.restHandler = restHandler;
        jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
    }

    protected RESTResponse handleWithTokenVerification(HttpExchange exchange, Function<HttpExchange, RESTResponse> handler) {
        try {
            if(exchange.getRequestHeaders().containsKey(HEADER_AUTHENTICATION)) {
                var tokenString = exchange.getRequestHeaders().get(HEADER_AUTHENTICATION);
                var token = jwtVerifier.verify(tokenString.get(0));

                var response = handler.apply(exchange);
                response.setResponseBody(response.getResponseBody() + "\ngot token: " + token.getSubject() + " " + token.getKeyId());
                return response;
            } else {
                return AUTHENTICATION_ERROR_RESPONSE;
            }
        } catch(JWTVerificationException jwte) {
            jwte.printStackTrace();
            return AUTHENTICATION_ERROR_RESPONSE;
        }
    }

    @Override
    public RESTResponse handleGET(HttpExchange exchange) {
        return handleWithTokenVerification(exchange, restHandler::handleGET);
    }

    @Override
    public RESTResponse handlePOST(HttpExchange exchange) {
        return handleWithTokenVerification(exchange, restHandler::handlePOST);
    }

    @Override
    public RESTResponse handlePUT(HttpExchange exchange) {
        return handleWithTokenVerification(exchange, restHandler::handlePUT);
    }

    @Override
    public RESTResponse handlePATCH(HttpExchange exchange) {
        return handleWithTokenVerification(exchange, restHandler::handlePATCH);
    }

    @Override
    public RESTResponse handleDELETE(HttpExchange exchange) {
        return handleWithTokenVerification(exchange, restHandler::handleDELETE);
    }
}
