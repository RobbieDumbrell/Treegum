package Controllers;

import db.DBAdvert;
import db.DBHelper;
import models.Advert;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import static spark.Spark.*;

public class MainController {

    public static void main(String[] args) {

        staticFileLocation("/public");

        File uploadDir = new File("upload");
        uploadDir.mkdir();

        staticFiles.externalLocation("upload");

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


        //        UPLOAD IMAGE TRY
        get("/upload_image", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/adverts/upload_image.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        post("/upload_image", (req, res) -> {

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");


            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("image").getInputStream()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo(req, tempFile);

            Advert advertExample =  DBHelper.findById(Advert.class, 261);
            advertExample.setUploadedImageURL(tempFile.getFileName().toString());
            DBHelper.update(advertExample);

//            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

            res.redirect("/");
            return null;

        });

    }

    // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("image")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}

