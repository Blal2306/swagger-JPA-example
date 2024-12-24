package com.app.experiment.prototype_app.DTO;

import com.app.experiment.prototype_app.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeDTO {
    private Long id;
    private String description;
    @JsonProperty("prep_time_in_minutes")
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientsDTO> ingredients = new HashSet<>();
    private String image; // using string to store image
    private Difficulty difficulty;
    private Notes notes;
    private Set<CategoryDTO> categories = new HashSet<>();

    public byte[] getImage() {
        return image != null ? image.getBytes(StandardCharsets.UTF_8) : null;
    }

    public void setImage(byte[] image) {
        this.image = image != null ? new String(image, StandardCharsets.UTF_8) : null;
    }
}
