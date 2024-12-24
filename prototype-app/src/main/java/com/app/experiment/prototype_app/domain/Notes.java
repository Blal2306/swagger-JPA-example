package com.app.experiment.prototype_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Recipe recipe;

    @Column(columnDefinition = "TEXT")
    private String recipeNotes;

    public Notes(String s) {
        this.recipeNotes = s;
    }
    public Notes setRecipe(Recipe r) {
        this.recipe = r;
        return this;
    }
}
