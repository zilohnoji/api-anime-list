package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.Category;
import jakarta.persistence.*;

@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Categories() {
    }

    public Categories(Long id, Category category) {
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }
}