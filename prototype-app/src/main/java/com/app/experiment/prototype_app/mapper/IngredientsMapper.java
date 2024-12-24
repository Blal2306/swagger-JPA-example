package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientsMapper {
    IngredientsMapper INSTANCE = Mappers.getMapper(IngredientsMapper.class);
    IngredientsDTO ingredientsToIngredientsDTO(Ingredient ingredients);
    Ingredient ingredientsDTOToIngredients(IngredientsDTO ingredientsDTO);
}
