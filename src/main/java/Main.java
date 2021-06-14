import HTTP.Authentication.JWTRESTDecorator;
import HTTP.BaseRESTHandler;
import HTTP.RESTResponse;
import HTTP.RESTServer;
import WarClash.Model.Entity.WarClashUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.net.httpserver.HttpExchange;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RESTServer restServer = new RESTServer(8000);

        BaseRESTHandler bekRESTHandler = new BaseRESTHandler() {
            @Override
            public RESTResponse handleGET(HttpExchange exchange) {
                return new RESTResponse(200, "GET BEK");
            }

            @Override
            public RESTResponse handlePOST(HttpExchange exchange) {
                return new RESTResponse(200, "POST BEK");
            }

            @Override
            public RESTResponse handlePUT(HttpExchange exchange) {
                return new RESTResponse(200, "PUT BEK");
            }

            @Override
            public RESTResponse handlePATCH(HttpExchange exchange) {
                return new RESTResponse(200, "PATCH BEK");
            }
        };
        restServer.addEndPoint("/bek", new JWTRESTDecorator(bekRESTHandler));

        restServer.start();
        System.out.println("Server started");

        System.out.println(JWT.create().withSubject("warclash").withKeyId("111").sign(Algorithm.HMAC256(JWTRESTDecorator.SECRET_KEY)));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-cock");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        WarClashUser warClashUser = new WarClashUser();
        warClashUser.setName("Cock Cockington");
        entityManager.persist(warClashUser);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
