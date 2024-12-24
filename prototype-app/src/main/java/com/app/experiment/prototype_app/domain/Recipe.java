package com.app.experiment.prototype_app.domain;

import lombok.*;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Column(columnDefinition = "TEXT")
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String image; // using string to store image

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe(String description, int prepTime, int cookTime, int servings, String source, String url) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
    }

    // Setters and business logic methods

    // Set the notes property of the recipe
    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Recipe setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Recipe setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Recipe setDirections(String directions) {
        this.directions = directions;
        return this;
    }

    // Getters
    public Set<Category> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImage() {
        return image.getBytes(StandardCharsets.UTF_8);
    }

    public void setImage(byte[] image) {
        String result = new String(image, StandardCharsets.UTF_8);
        this.image = result;
    }
}
