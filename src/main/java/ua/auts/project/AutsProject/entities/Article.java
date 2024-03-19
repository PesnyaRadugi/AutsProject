package ua.auts.project.AutsProject.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title, content;
    private Date datePublished;

    public Article() {
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @PrePersist
    protected void onCreate () {
        datePublished = new Date();
    }

}
