package com.example.academics.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 60)
    private String title;

    public Course setPrice(String price) {
        this.price = price;
        return this;
    }

    @Column
    private String price;

    public String getCategory() {
        return category;
    }

    @Column
    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    @Column
    private String description;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Lob
    private byte[] photo;
    private LocalDateTime createdDate;
    @ManyToOne
    private Users createdBy;

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Course setContent(String content) {
        this.content = content;
        return this;
    }

    public byte[] getPhoto() {
        return photo;
    }

    @Transactional    public Course setPhoto(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Course setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public Course setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course blog = (Course) o;
        return Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

