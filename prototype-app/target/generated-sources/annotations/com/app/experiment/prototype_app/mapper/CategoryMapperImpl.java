package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.CategoryDTO;
import com.app.experiment.prototype_app.domain.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T15:06:14-0600",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setDescription( category.getDescription() );

        return categoryDTO;
    }

    @Override
    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setDescription( categoryDTO.getDescription() );

        return category;
    }
}
