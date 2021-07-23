package com.codeup.springblog.models;

//import persistence first
import javax.persistence.*;


//Said: 'this id is going to be the main identifier that exists within this entity/class. going to be referenced to this id'
//strategy: this will generate automatically in the database
@Entity
//can connect this to database and turn into a table:
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    to include a column; do not accept null as a value, set a max length
    @Column(nullable = false, length = 140)
    private String title;

    @Column(nullable = false)
    private String description;


    public Ad(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

//    Have to have empty constructor for Hibernate to work
    public Ad() {
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
}
