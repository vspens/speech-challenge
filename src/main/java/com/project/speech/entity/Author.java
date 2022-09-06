package com.project.speech.entity;

import javax.persistence.*;

/**
 * @author Vince Spencer Historia
 */
@Entity
@Table(name= "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "NAME", nullable = false, unique = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
