package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.domain.Ingredient;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T15:06:13-0600",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IngredientsMapperImpl implements IngredientsMapper {

    @Override
    public IngredientsDTO ingredientsToIngredientsDTO(Ingredient ingredients) {
        if ( ingredients == null ) {
            return null;
        }

        IngredientsDTO ingredientsDTO = new IngredientsDTO();

        ingredientsDTO.setId( ingredients.getId() );
        ingredientsDTO.setDescription( ingredients.getDescription() );
        if ( ingredients.getAmount() != null ) {
            ingredientsDTO.setAmount( ingredients.getAmount().doubleValue() );
        }

        return ingredientsDTO;
    }

    @Override
    public Ingredient ingredientsDTOToIngredients(IngredientsDTO ingredientsDTO) {
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
}
