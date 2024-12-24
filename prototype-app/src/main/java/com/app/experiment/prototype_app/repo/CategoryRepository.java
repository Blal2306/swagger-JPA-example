package com.app.experiment.prototype_app.repo;

import com.app.experiment.prototype_app.domain.Category;
import com.app.experiment.prototype_app.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by jt on 6/13/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByDescription(String description);

    Optional<Recipe> getRecipesById(Long id);
}
