package org.dbp.lecture.webserver;

import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
public class StudentService extends AbstractVerticle {

    public static HashMap<String, Student> data = new HashMap<>();

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        HttpServer server = vertx.createHttpServer();
        // "/" GET,    Hi! there
        Router router = Router.router(vertx);

        router.get("/").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                JSONArray arr = new JSONArray();
                for( String val: data.keySet()){
                    arr.put(val);
                }
                routingContext.response().end(arr.toString());
            }
        });


        router.post("/:id").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                // JSONObject 와 헷갈리지 말것
//                JsonObject obj = routingContext.body().asJsonObject();


                // / if you get request , do something , response

                String id = routingContext.pathParam("id");
                String title = routingContext.queryParam("title").get(0);
                data.put(id, new Student(id, title));
                routingContext.response().end("success");
            }
        });

        router.get("/:id").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                // / if you get request , do something , response
                String id = routingContext.pathParam("id");
                Student student = data.get(id);
                JSONObject obj = new JSONObject();
                obj.put("id", student.getId());
                obj.put("title", student.getTitle());
                routingContext.response().end(obj.toString());
            }
        });

        router.delete("/:id").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                if(data.containsKey(routingContext.pathParam("id"))){
                    data.remove(routingContext.pathParam("id"));
                    routingContext.response().end();
                }else{
                    routingContext.response().setStatusCode(404).end();
                }

            }
        });

        server.requestHandler(router).listen(8080);

    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new StudentService());
    }
}

