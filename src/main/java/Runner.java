import db.DBAdvert;
import db.DBComment;
import db.DBHelper;
import db.DBUser;
import models.*;

import java.util.HashMap;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        DBHelper.deleteAll(Comment.class);
        DBHelper.deleteAll(Rating.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Advert.class);


        User user1 = new User("phael90", "Raphael", "Ugha", "raph@outlook.com");
        User user2 = new User("robbieisthebest", "Robbie", "Dumbrell", "robbie@outlook.com");
        User user3 = new User("iamjohn", "John", "Johnson", "john@outlook.com");
        User user4 = new User("auseraccount", "A", "User", "a@outlook.com");
        User user5 = new User("kirstin77", "Kirstin", "Kirstinson", "kirstin@outlook.com");
        User user6 = new User("tahnee988", "Tahnee", "Surname", "tahnee@outlook.com");
        User user7 = new User("joanna22", "Joanna", "Joannason", "joanna@outlook.com");
        User user8 = new User("euan33", "Euan", "Euanson", "euan@outlook.com");
        User user9 = new User("aaa", "Andy", "Anderson", "andy@outlook.com");
        User user10 = new User("bbb", "Ben", "Benson", "ben@outlook.com");

        DBHelper.save(user1);
        DBHelper.save(user2);
        DBHelper.save(user3);
        DBHelper.save(user4);
        DBHelper.save(user5);
        DBHelper.save(user6);
        DBHelper.save(user7);
        DBHelper.save(user8);
        DBHelper.save(user9);
        DBHelper.save(user10);

        Advert advert1 = new Advert("An amazing car", "This is a car that is amazing", Category.VEHICLES, 10000, user1);
        Advert advert4 = new Advert("A bad car", "This is a car that is amazing", Category.VEHICLES, 15000, user1);
        Advert advert2 = new Advert("An incredible laptop", "This is a laptop that is incredible", Category.ELECTRONICS, 1000, user1);
        Advert advert3 = new Advert("Baby mouse for sale", "Want ot buy a baby mouse?", Category.ANIMALS, 10, user2);
        Advert advert5 = new Advert("A Dinosaur", "This is a real life dinosaur - I swear.", Category.ANIMALS, 50000, user2);
        Advert advert6 = new Advert("A Wardrobe", "Used wardrobe - message for details", Category.HOUSEHOLDITEM, 500, user2);
        Advert advert7 = new Advert("Double Bed", "New Double Bed frame for sale.", Category.HOUSEHOLDITEM, 800, user3);
        Advert advert8 = new Advert("Pen", "Really nice pen, trust me", Category.MISCELLANEOUS, 32, user9);
        Advert advert9 = new Advert("Computer Monitor", "20 inch computer monitor", Category.ELECTRONICS, 100, user5);
        Advert advert10 = new Advert("Used Motorbike", "Used motorbike for sale. Message for details", Category.VEHICLES, 1400, user2);

        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);
        DBHelper.save(advert4);
        DBHelper.save(advert5);
        DBHelper.save(advert6);
        DBHelper.save(advert7);
        DBHelper.save(advert8);
        DBHelper.save(advert9);
        DBHelper.save(advert10);

//        List<Advert> user1Adverts = DBUser.getAllAdverts(user1);
//
//        User foundUser = DBHelper.findById(User.class, 1);
//        foundUser.setUsername("digory");
//        DBHelper.update(foundUser);

//        DBHelper.delete(foundUser);

//        Advert foundAdvert = DBHelper.findById(Advert.class, 1);
//        DBHelper.delete(foundAdvert);

//        List<User> allUsers = DBHelper.getAll(User.class);
//        List<Advert> allAdverts = DBHelper.getAll(Advert.class);

//        List<Advert> advertsVehicleByCategory = DBAdvert.getAllAdvertsByCategory(Category.VEHICLES);

//        Advert foundAdvert = DBHelper.findById(Advert.class, 9);
//        DBAdvert.archiveAdvert(foundAdvert);

//        List<Advert> allArchivedAdverts = DBAdvert.getAllArchivedAdverts();
//        List<Advert> allActiveAdverts = DBAdvert.getAllActiveAdverts();
//
//        List<Advert> searchedAdverts = DBAdvert.getAllSearchedActiveAdverts("car");
//
        Comment comment1 = new Comment(user1, advert1, "Hello, I really like the look of this!");
        Comment comment2 = new Comment(user10, advert2, "This looks so cool! Can you reply to my e-mail?");
        Comment comment3 = new Comment(user8, advert3, "Hello, could you send me your phone number?");
        Comment comment4 = new Comment(user6, advert1, "Is this for real?");
        Comment comment5 = new Comment(user2, advert4, "This is a random comment.");
//
        DBHelper.save(comment1);
        DBHelper.save(comment2);
        DBHelper.save(comment3);
        DBHelper.save(comment4);
        DBHelper.save(comment5);
//
//        List<Comment> user1Comments = DBComment.getAllCommentsByUser(user1);
//        List<Comment> advert1Comments = DBComment.getAllCommentsForAdvert(advert1);

        Rating rating1 = new Rating(user1, user9, RatingValue.FOUR, "All good, really nice to deal with.");
        Rating rating2 = new Rating(user8, user4, RatingValue.THREE, "Average.");
        Rating rating3 = new Rating(user9, user4, RatingValue.TWO, "Not the best.");
        Rating rating4 = new Rating(user5, user3, RatingValue.ONE, "Item was not as advertised! Beware!");
        Rating rating5 = new Rating(user3, user2, RatingValue.ONE, "I just didn't really like what I bought.");
        Rating rating6 = new Rating(user6, user1, RatingValue.FIVE, "This seller was incredible!");
        Rating rating7 = new Rating(user10, user1, RatingValue.FIVE, "I love this person.");
        Rating rating8 = new Rating(user9, user6, RatingValue.THREE, "It was ok.");
        Rating rating9 = new Rating(user9, user8, RatingValue.TWO, "Item was terrible but seller was nice.");
        Rating rating10 = new Rating(user2, user10, RatingValue.TWO, "");

        DBHelper.save(rating1);
        DBHelper.save(rating2);
        DBHelper.save(rating3);
        DBHelper.save(rating4);
        DBHelper.save(rating5);
        DBHelper.save(rating6);
        DBHelper.save(rating7);
        DBHelper.save(rating8);
        DBHelper.save(rating9);
        DBHelper.save(rating10);

//        DBHelper.delete(user1);
//        DBHelper.delete(user2);


//        List<User> searchUsers = DBUser.getAllSearchedActiveUsers("robbie");
//
//        Double averageRatinguser2 = DBUser.calculateAverageRatingForUser(user2);
        List<User> searchUsers = DBUser.getAllSearchedActiveUsers("danny");


        Boolean doesphael90Exist = DBUser.doesUserNameExist("phael90");
        Boolean doesphael91Exist = DBUser.doesUserNameExist("phael91");

        List<Advert> getLowToHighPrice = DBAdvert.getAllActiveAdvertsAscendingPrice();
        List<Advert> getHighToLowPrice = DBAdvert.getAllActiveAdvertsDescendingPrice();

        List<Advert> getLowToHighPriceVehicles = DBAdvert.getAllActiveAdvertsAscendingPriceCategory(Category.VEHICLES);
        List<Advert> getHighToLowPriceVehicles = DBAdvert.getAllActiveAdvertsDescendingPriceCategory(Category.VEHICLES);

        List<Advert> getAdvertsSortedByCategoryAtoZ = DBAdvert.getAllActiveAdvertsSortedByCategoryAtoZ();

//        List<User> usersWithAverageRatingsAsc = DBUser.getAllSortedByAverageRatingAscending();
//        List<User> usersWithAverageRatingsDesc = DBUser.getAllSortedByAverageRatingDescending();
        List<User> usersWithAverageRatingsAtoZ = DBUser.getAllSortedByUserNameAtoZ();

    }

}
