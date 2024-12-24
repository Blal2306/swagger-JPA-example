package com.app.experiment.prototype_app.bootstrap;

import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.*;
import com.app.experiment.prototype_app.repo.CategoryRepository;
import com.app.experiment.prototype_app.repo.RecipeRepository;
import com.app.experiment.prototype_app.repo.StudentRepo;
import com.app.experiment.prototype_app.repo.UnitOfMeasureRepository;
import com.app.experiment.prototype_app.service.RecipeService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class ReceipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final StudentRepo studentRepo;

    //testing the receipe service
    private final RecipeService recipeService;

    public ReceipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, StudentRepo studentRepo, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.studentRepo = studentRepo;
        this.recipeService = recipeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    @Transactional
    public void initData() {
        // Populate student repo
        studentRepo.save(new Student("John Doe", "john.doe@example.com"));
        studentRepo.save(new Student("Jane Smith", "jane.smith@example.com"));
        studentRepo.save(new Student("Michael Johnson", "michael.johnson@example.com"));
        studentRepo.save(new Student("Emily Davis", "emily.davis@example.com"));
        studentRepo.save(new Student("William Brown", "william.brown@example.com"));
        studentRepo.save(new Student("Olivia White", "olivia.white@example.com"));
        studentRepo.save(new Student("James Black", "james.black@example.com"));
        studentRepo.save(new Student("Sophia Green", "sophia.green@example.com"));
        studentRepo.save(new Student("Daniel Harris", "daniel.harris@example.com"));
        studentRepo.save(new Student("Charlotte Clark", "charlotte.clark@example.com"));

        System.out.println(studentRepo.count() + " students saved.");

        // Populate category repo
        categoryRepository.save(new Category("American"));
        categoryRepository.save(new Category("Italian"));
        categoryRepository.save(new Category("Mexican"));
        categoryRepository.save(new Category("Chinese"));
        categoryRepository.save(new Category("Indian"));
        categoryRepository.save(new Category("Japanese"));
        categoryRepository.save(new Category("Mediterranean"));
        categoryRepository.save(new Category("French"));
        categoryRepository.save(new Category("Thai"));
        categoryRepository.save(new Category("Vietnamese"));

        // Populate unit of measure repo
        unitOfMeasureRepository.save(new UnitOfMeasure("Teaspoon"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Tablespoon"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Cup"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Ounce"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Pint"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Quart"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Gallon"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Milliliter"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Liter"));
        unitOfMeasureRepository.save(new UnitOfMeasure("Gram"));

        // Create 10 recipes
        for (int i = 1; i <= 10; i++) {
            Recipe recipe = new Recipe("Recipe " + i, 10 + i, 20 + i, 4, "Source " + i, "URL " + i);

            // Create the ingredients for each recipe
            Set<Ingredient> ingredients = new HashSet<>();
            ingredients.add(new Ingredient("Recipe " + i + " - Ingredient 1", new BigDecimal(10 + i), unitOfMeasureRepository.findByDescription("Teaspoon").get(0)));
            ingredients.add(new Ingredient("Recipe " + i + " - Ingredient 2", new BigDecimal(5 + i), unitOfMeasureRepository.findByDescription("Cup").get(0)));
            ingredients.add(new Ingredient("Recipe " + i + " - Ingredient 3", new BigDecimal(7 + i), unitOfMeasureRepository.findByDescription("Ounce").get(0)));

            recipe.setDifficulty(Difficulty.values()[i % Difficulty.values().length]); // Assign a difficulty from the enum
            recipe.setNotes(new Notes("Recipe " + i + " - Notes").setRecipe(recipe));
            recipe.setImage(new byte[]{1, 2, 3, 4, 5});
            ingredients.forEach(recipe::addIngredient);  // Add ingredients to the recipe

            // Create categories dynamically for each recipe
            Category cat1 = categoryRepository.findByDescription(i % 2 == 0 ? "Italian" : "American").get(0);
            Category cat2 = categoryRepository.findByDescription(i % 3 == 0 ? "Mexican" : "Chinese").get(0);

            recipe.getCategories().add(cat1);
            recipe.getCategories().add(cat2);

            recipe.setDirections("Recipe " + i + " - Directions");
            recipeRepository.save(recipe);
        }

        System.out.println("recipes saved... Below is the list of receipes ");

    }

    public void printRecipes(Set<RecipeDTO> recipes) {
        recipes.forEach(recipe -> {
            System.out.println("Recipe ID: " + recipe.getId());
            System.out.println("Recipe Description: " + recipe.getDescription());
            System.out.println("Recipe Prep Time: " + recipe.getPrepTime());
            System.out.println("Recipe Cook Time: " + recipe.getCookTime());
            System.out.println("Recipe Servings: " + recipe.getServings());
            System.out.println("Recipe Source: " + recipe.getSource());
            System.out.println("Recipe URL: " + recipe.getUrl());
            System.out.println("Recipe Directions: " + recipe.getDirections());
            System.out.println("Recipe Image: " + recipe.getImage());
            System.out.println("Recipe Difficulty: " + recipe.getDifficulty());
            System.out.println("Recipe Notes: " + recipe.getNotes());
            System.out.println("Recipe Categories: ");
            recipe.getCategories().forEach(category -> System.out.println("\t" + category.getDescription()));
            System.out.println("Recipe Ingredients: ");
            recipe.getIngredients().forEach(ingredient -> {
                System.out.println("\tIngredient ID: " + ingredient.getId());
                System.out.println("\tIngredient Description: " + ingredient.getDescription());
                System.out.println("\tIngredient Amount: " + ingredient.getAmount());
            });
        });
    }
}