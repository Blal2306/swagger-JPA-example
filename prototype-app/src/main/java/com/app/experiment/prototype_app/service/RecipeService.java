package com.app.experiment.prototype_app.service;

import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.DTO.NotesDTO;
import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.Category;
import com.app.experiment.prototype_app.domain.Ingredient;
import com.app.experiment.prototype_app.domain.Notes;
import com.app.experiment.prototype_app.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    //get all recipes
    List<RecipeDTO> getRecipes();

    //get recipe by id
    RecipeDTO findById(Long l);

    //save a receipe
    RecipeDTO saveRecipe(RecipeDTO recipe);

    //delete a recipe by id
    void deleteById(Long idToDelete);

    //get ingredients of a receipe
    Set<IngredientsDTO> getIngredients(Long recipeId);

    //get Notes of a recipe
    NotesDTO getNotes(Long recipeId);

    //print a recipe
    void printRecipe(Recipe rec);

}
