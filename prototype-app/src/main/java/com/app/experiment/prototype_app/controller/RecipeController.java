package com.app.experiment.prototype_app.controller;

import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.DTO.NotesDTO;
import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.Category;
import com.app.experiment.prototype_app.domain.Ingredient;
import com.app.experiment.prototype_app.repo.CategoryRepository;
import com.app.experiment.prototype_app.repo.RecipeRepository;
import com.app.experiment.prototype_app.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    //repo
    private final RecipeRepository recipeRepository;

    private final CategoryRepository categoryRepository;



    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "recipes", method = RequestMethod.GET)
    public List<RecipeDTO> getRecipes(){
        return recipeService.getRecipes();
    }

    @GetMapping(value = "recipes/{id}")
    public RecipeDTO showByID(Long id){
        return recipeService.findById(id);
    }

    @PostMapping(value = "save")
    public RecipeDTO saveRecipe(@RequestBody RecipeDTO recipeDTO) throws InterruptedException {
        //store in the DB and read it back from the DB
        RecipeDTO temp = recipeService.saveRecipe(recipeDTO);
        recipeService.printRecipe(recipeRepository.findById(temp.getId()).get());
        return temp;
    }

    @GetMapping(value = "delete/{id}")
    public String deleteRecipe(Long id){
        recipeService.deleteById(id);
        return "Recipe deleted successfully";
    }

    @GetMapping(value = "recipe/{id}")
    public NotesDTO getNotes(Long id){
        return recipeService.getNotes(id);
    }

    @GetMapping(value = "getIngredients/{id}")
    public Set<IngredientsDTO> getIngredients(Long id){
        return recipeService.getIngredients(id);
    }
}
