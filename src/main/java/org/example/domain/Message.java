package org.example.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)         //AUTO - по умолчанию. Можно не ставить
    private Integer id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long")   //Validation
    private String text;
    @Length(max = 255, message = "Tag too long")
    private String tag;
    private String filename;            //весь путь - в properties

    @ManyToOne(fetch = FetchType.EAGER)
    // Связь. Одному author соотвествуют множество messages
    @JoinColumn(name = "user_id")                                                //по умолчанию было б author_id
    private User author;

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {                   //Это закидоны из groovy. Всё методы getSomething могут быть заменены на обращение к полю (даже если оно не существует) с именем something
        return author != null ? author.getUsername() : "none";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
