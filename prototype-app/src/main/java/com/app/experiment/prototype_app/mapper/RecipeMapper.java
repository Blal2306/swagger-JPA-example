package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
    RecipeDTO recipeToRecipeDTO(Recipe recipe);
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);
}
