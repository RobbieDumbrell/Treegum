package Controllers;

import db.DBHelper;
import db.DBUser;
import models.Advert;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController() {
        setUpRoutes();
    }

    public static void setUpRoutes() {

        //    INDEX
        get("/users", (req, res) -> {

            List<User> allUsers;

            String sort = req.queryParams("sort");

            if (sort == null) {
                allUsers = DBHelper.getAll(User.class);
            }
//            else if (sort.equals("High to Low")) {
//                allUsers = DBUser.getAllSortedByAverageRatingAscending();
//            } else if (sort.equals("Low to High")) {
//                allUsers = DBUser.getAllSortedByAverageRatingDescending();
//            } else if (sort.equals("Username A to Z")) {
                allUsers = DBUser.getAllSortedByUserNameAtoZ();
//            } else {
//                allUsers = DBHelper.getAll(User.class);
//            }

            String searchEntry = req.queryParams("search");
            List<User> searchResults = DBUser.getAllSearchedActiveUsers(searchEntry);

            HashMap<String, Object> model = new HashMap<>();
            model.put("allUsers", allUsers);
            model.put("searchResults", searchResults);
            model.put("template", "templates/users/index.vtl");

            if (searchEntry != null && searchResults.size() == 0) {
                model.put("template", "templates/users/indexSearchNotFound.vtl");
            } else {
                model.put("template", "templates/users/index.vtl");
            }

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //    INDEX USER NOT FOUND
        get("/users", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //    NEW
        get("/users/new", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/new.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //    CREATE
        post("/users", (req, res) -> {

            String username = req.queryParams("username");
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String eMail = req.queryParams("eMail");

            Boolean doesUserNameExist = DBUser.doesUserNameExist(username);

            if (!doesUserNameExist) {
                User newUser = new User(username, firstName, lastName, eMail);
                DBHelper.save(newUser);

                res.redirect("/users");

            } else {
                res.redirect("/users/new/username_exists");
            }

            return null;

        });

        //    NEW USERNAME EXISTS
        get("/users/new/username_exists", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/new_username_exists.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    SHOW
        get("/users/:id", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User user = DBHelper.findById(User.class, userId);

            List<Advert> userActiveAdverts = DBUser.getAllActiveAdverts(user);
            List<Advert> userArchivedAdverts = DBUser.getAllArchivedAdverts(user);

            Double averageRating = user.calculateAverageRatingReceived();

            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("userActiveAdverts", userActiveAdverts);
            model.put("userArchivedAdverts", userArchivedAdverts);
            model.put("averageRating", averageRating);
            model.put("template", "templates/users/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    EDIT
        get("/users/:id/edit", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User editingUser = DBHelper.findById(User.class, userId);

            HashMap<String, Object> model = new HashMap<>();
            model.put("editingUser", editingUser);
            model.put("template", "templates/users/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    UPDATE
        post("/users/:id", (req, res) -> {

            String username = req.queryParams("username");
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String eMail = req.queryParams("eMail");

            int userId = Integer.parseInt(req.params(":id"));
            User editingUser = DBHelper.findById(User.class, userId);

            editingUser.setUsername(username);
            editingUser.setFirstName(firstName);
            editingUser.setLastName(lastName);
            editingUser.seteMail(eMail);

            DBHelper.update(editingUser);

            res.redirect("/users/" + userId);
            return null;
        });

//        CONFIRM DELETE
        get("/users/:id/delete/confirm", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User deletingUser = DBHelper.findById(User.class, userId);

            HashMap<String, Object> model = new HashMap<>();
            model.put("deletingUser", deletingUser);
            model.put("template", "templates/users/confirm_delete.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        //    DELETE
        post("/users/:id/delete", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User deletingUser = DBHelper.findById(User.class, userId);

            DBHelper.delete(deletingUser);

            res.redirect("/users");
            return null;
        });

    }

}
