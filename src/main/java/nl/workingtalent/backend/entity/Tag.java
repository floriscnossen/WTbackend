package nl.workingtalent.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false, length = 100)
    private String name;


    //Constructors
    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
