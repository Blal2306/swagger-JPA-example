package com.app.experiment.prototype_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"receipes"})
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<Recipe> receipes = new HashSet<>();

    public Category(String description) {
        this.description = description;
    }

    // Explicitly initialize the collection if it's null or empty
    public void setRecipe(Recipe recipe) {
        if (this.receipes == null) {
            this.receipes = new HashSet<>();
        }
        this.receipes.add(recipe);
    }

    // You can also force initialization of the lazy-loaded collection like this:
    public void initializeReceipes() {
        this.receipes.size(); // Forces the initialization of the lazy-loaded collection
    }

}