package org.dbp.lecture.webserver;

import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class MyVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        // 이곳에 있는 작업이 수행된다.
//        System.out.println("Hello Vert.X");
        HttpServer server =  vertx.createHttpServer();

        Router router = Router.router(vertx);
//        router.get("/").handler(new Handler<RoutingContext>() {
//            @Override
//            public void handle(RoutingContext routingContext) {
//                routingContext.response().end("Hi there");
//            }
//        });

                router.get("/ab").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                List<String> titles = routingContext.queryParam("title");
                List<String> names = routingContext.queryParam("name");

                routingContext.response().end("GoodBye " + titles + names );
            }
        });

        router.get("/abc").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                List<String> titles = routingContext.queryParam("title");
                List<String> names = routingContext.queryParam("name");

                routingContext.response().end("Hi " + titles + names);
            }
        });

        router.post("/abc").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                List<String> titles = routingContext.queryParam("title");
                List<String> names = routingContext.queryParam("name");

                routingContext.response().end("Hi " + titles + names);
            }
        });

//        server.listen(8080);
        server.requestHandler(router).listen(8080);
    }


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        2가지 방법 다 가능
//        그러나 위 방법이 여러개를 만들 수 있어서 동시성 문제 해결 가능
        vertx.deployVerticle("org.dbp.lecture.webserver.MyVerticle",  new DeploymentOptions().setInstances(4));
//        vertx.deployVerticle(new MyVerticle());
    }
}
