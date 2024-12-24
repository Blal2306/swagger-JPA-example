package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.CategoryDTO;
import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.Category;
import com.app.experiment.prototype_app.domain.Ingredient;
import com.app.experiment.prototype_app.domain.Recipe;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T15:06:14-0600",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class RecipeMapperImpl implements RecipeMapper {

    @Override
    public RecipeDTO recipeToRecipeDTO(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDTO recipeDTO = new RecipeDTO();

        byte[] image = recipe.getImage();
        if ( image != null ) {
            recipeDTO.setImage( Arrays.copyOf( image, image.length ) );
        }
        recipeDTO.setId( recipe.getId() );
        recipeDTO.setDescription( recipe.getDescription() );
        recipeDTO.setPrepTime( recipe.getPrepTime() );
        recipeDTO.setCookTime( recipe.getCookTime() );
        recipeDTO.setServings( recipe.getServings() );
        recipeDTO.setSource( recipe.getSource() );
        recipeDTO.setUrl( recipe.getUrl() );
        recipeDTO.setDirections( recipe.getDirections() );
        recipeDTO.setIngredients( ingredientSetToIngredientsDTOSet( recipe.getIngredients() ) );
        recipeDTO.setDifficulty( recipe.getDifficulty() );
        recipeDTO.setNotes( recipe.getNotes() );
        recipeDTO.setCategories( categorySetToCategoryDTOSet( recipe.getCategories() ) );

        return recipeDTO;
    }

    @Override
    public Recipe recipeDTOToRecipe(RecipeDTO recipeDTO) {
        if ( recipeDTO == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setNotes( recipeDTO.getNotes() );
        recipe.setIngredients( ingredientsDTOSetToIngredientSet( recipeDTO.getIngredients() ) );
        recipe.setDifficulty( recipeDTO.getDifficulty() );
        recipe.setCategories( categoryDTOSetToCategorySet( recipeDTO.getCategories() ) );
        recipe.setDirections( recipeDTO.getDirections() );
        byte[] image = recipeDTO.getImage();
        if ( image != null ) {
            recipe.setImage( Arrays.copyOf( image, image.length ) );
        }
        recipe.setId( recipeDTO.getId() );
        recipe.setDescription( recipeDTO.getDescription() );
        recipe.setPrepTime( recipeDTO.getPrepTime() );
        recipe.setCookTime( recipeDTO.getCookTime() );
        recipe.setServings( recipeDTO.getServings() );
        recipe.setSource( recipeDTO.getSource() );
        recipe.setUrl( recipeDTO.getUrl() );

        return recipe;
    }

    protected IngredientsDTO ingredientToIngredientsDTO(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        IngredientsDTO ingredientsDTO = new IngredientsDTO();

        ingredientsDTO.setId( ingredient.getId() );
        ingredientsDTO.setDescription( ingredient.getDescription() );
        if ( ingredient.getAmount() != null ) {
            ingredientsDTO.setAmount( ingredient.getAmount().doubleValue() );
        }

        return ingredientsDTO;
    }

    protected Set<IngredientsDTO> ingredientSetToIngredientsDTOSet(Set<Ingredient> set) {
        if ( set == null ) {
            return null;
        }

        Set<IngredientsDTO> set1 = new LinkedHashSet<IngredientsDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Ingredient ingredient : set ) {
            set1.add( ingredientToIngredientsDTO( ingredient ) );
        }

        return set1;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setDescription( category.getDescription() );

        return categoryDTO;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDTO( category ) );
        }

        return set1;
    }

    protected Ingredient ingredientsDTOToIngredient(IngredientsDTO ingredientsDTO) {
        if ( ingredientsDTO == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( ingredientsDTO.getId() );
        ingredient.setDescription( ingredientsDTO.getDescription() );
        if ( ingredientsDTO.getAmount() != null ) {
            ingredient.setAmount( BigDecimal.valueOf( ingredientsDTO.getAmount() ) );
        }

        return ingredient;
    }

    protected Set<Ingredient> ingredientsDTOSetToIngredientSet(Set<IngredientsDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Ingredient> set1 = new LinkedHashSet<Ingredient>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IngredientsDTO ingredientsDTO : set ) {
            set1.add( ingredientsDTOToIngredient( ingredientsDTO ) );
        }

        return set1;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setDescription( categoryDTO.getDescription() );

        return category;
    }

    protected Set<Category> categoryDTOSetToCategorySet(Set<CategoryDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : set ) {
            set1.add( categoryDTOToCategory( categoryDTO ) );
        }

        return set1;
    }
}
