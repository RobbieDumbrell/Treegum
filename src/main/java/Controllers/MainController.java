package Controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        staticFileLocation("/public");

        AdvertController advertController = new AdvertController();
        UserController userController = new UserController();
        CommentController commentController = new CommentController();
        RatingsController ratingsController = new RatingsController();

//        HOME PAGE
        get("/", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
