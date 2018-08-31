package models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adverts")
public class Advert {
    private int id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private User user;
    private Boolean archived;
    private List<Comment> comments;
    private String uploadedImageURL;

    public Advert(String title, String description, Category category, double price, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.user = user;
        this.archived = false;
        this.comments = new ArrayList<>();
    }

    public Advert() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "archived")
    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @OneToMany(mappedBy = "advert")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void archive() {
        this.archived = true;
    }

    @Column(name = "uploaded_image_url")
    public String getUploadedImageURL() {
        return uploadedImageURL;
    }

    public void setUploadedImageURL(String uploadedImageURL) {
        this.uploadedImageURL = uploadedImageURL;
    }
}
