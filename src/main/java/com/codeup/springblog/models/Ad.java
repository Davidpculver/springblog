package com.codeup.springblog.models;

//import persistence first
//import javax.persistence.*;
//
//
////Said: 'this id is going to be the main identifier that exists within this entity/class. going to be referenced to this id'
////strategy: this will generate automatically in the database
//@Entity
////can connect this to database and turn into a table:
//@Table(name="ads")
//public class Ad {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
////    to include a column; do not accept null as a value, set a max length
//    @Column(nullable = false, length = 140)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//    @OneToOne
//
//
//    public Ad(long id, String title, String description) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//    }
//
//
//
////    Have to have empty constructor for Hibernate to work
//    public Ad() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 140)
    private String title;
    @Column(nullable = false)
    private String description;

    //    establishing to image
    @OneToOne
    private AdImage adImage;


    public Ad(){}

//    connecting to user - joincolumn binds their relationship together, ad an extra column to the table the represents user included
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    establish the join table for categories and ads. Similar to the connecting manager/employees table inside the employees db. A single ad can point to many different ads.
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ads_categories",
            joinColumns = {@JoinColumn(name = "ad_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )

    private List<Category> categories;


    public Ad(long id, String title, String description, AdImage adImage, User user, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adImage = adImage;
        this.user = user;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public AdImage getAdImage() {
        return adImage;
    }
    public void setAdImage(AdImage adImage) {
        this.adImage = adImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}