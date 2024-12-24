package com.app.experiment.prototype_app.service;

import com.app.experiment.prototype_app.DTO.CategoryDTO;
import com.app.experiment.prototype_app.DTO.IngredientsDTO;
import com.app.experiment.prototype_app.DTO.NotesDTO;
import com.app.experiment.prototype_app.DTO.RecipeDTO;
import com.app.experiment.prototype_app.domain.Category;
import com.app.experiment.prototype_app.domain.Ingredient;
import com.app.experiment.prototype_app.domain.Notes;
import com.app.experiment.prototype_app.domain.Recipe;
import com.app.experiment.prototype_app.mapper.CategoryMapper;
import com.app.experiment.prototype_app.mapper.IngredientsMapper;
import com.app.experiment.prototype_app.mapper.RecipeMapper;
import com.app.experiment.prototype_app.repo.CategoryRepository;
import com.app.experiment.prototype_app.repo.RecipeRepository;
import com.app.experiment.prototype_app.repo.StudentRepo;
import com.app.experiment.prototype_app.repo.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientsMapper ingredientMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientsMapper ingredientMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public List<RecipeDTO> getRecipes() {
        log.debug("Calling - getRecipes & returning RecipeDTO Set");

        List<Category> recipeCategories = categoryRepository.findAll();

        List<RecipeDTO> recipeSet = new ArrayList<>();
        recipeRepository.findAll().forEach(recipe -> {
            Set<Category> finalList = new HashSet<>();
            recipeCategories.stream().forEach(x -> x.getReceipes().forEach(y -> {
                if (y.getId() == recipe.getId()) {
                    finalList.add(x);
                }
            }));
            recipe.setCategories(finalList);

            Set<Ingredient> recipeIngredients = recipe.getIngredients();

            printRecipe(recipe);
            RecipeDTO recipeDTO = recipeMapper.recipeToRecipeDTO(recipe);
            recipeSet.add(recipeDTO);
        });

        System.out.println(recipeSet.size() + " recipes added to the DTO Recipe Set");
        return recipeSet;
    }

    @Override
    public RecipeDTO findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        Recipe recipe = recipeOptional.get();
        Set<Category> findList = new HashSet<>();
        categoryRepository.findAll().forEach(x -> x.getReceipes().forEach(y -> {
            if (y.getId().equals(recipe.getId())) {
                findList.add(x);
            }
        }));
        recipe.setCategories(findList);
        return recipeMapper.recipeToRecipeDTO(recipe);
    }

    @Override
    public RecipeDTO saveRecipe(RecipeDTO recipe) {
        Set<IngredientsDTO> ingredients = recipe.getIngredients();
        Set<CategoryDTO> categories = recipe.getCategories();
        Notes notes = recipe.getNotes();

        Recipe out = new Recipe();
        out.setDirections(recipe.getDirections());
        out.setPrepTime(recipe.getPrepTime());
        out.setCookTime(recipe.getCookTime());
        out.setServings(recipe.getServings());
        out.setSource(recipe.getSource());
        out.setUrl(recipe.getUrl());
        out.setDescription(recipe.getDescription());
        out.setImage(recipe.getImage());
        out.setDifficulty(recipe.getDifficulty());

        Set<Ingredient> ingredientsSet = new HashSet<>();
        ingredients.forEach(ing -> {
            ingredientsSet.add(new Ingredient(ing.getDescription(), new BigDecimal(ing.getAmount()), unitOfMeasureRepository.findByDescription("Teaspoon").get(0)));
        });

        ingredientsSet.forEach(out::addIngredient);

        out.setNotes(new Notes(notes.getRecipeNotes()).setRecipe(out));

        System.out.println("CATEGORIES: " + categories.size());
        categories.forEach(cat -> {
            List<Category> categoryList = categoryRepository.findByDescription(cat.getDescription());
            if (!categoryList.isEmpty()) {
                Category category = categoryList.get(0);
                category.getReceipes().add(out);
                out.getCategories().add(category);
            }
        });

        Recipe savedRecipe = recipeRepository.save(out);
        System.out.println("CONSTRUCTED RECIPE : " + savedRecipe.getId());
        return recipeMapper.recipeToRecipeDTO(savedRecipe);
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }

    @Override
    public Set<IngredientsDTO> getIngredients(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        Recipe recipe = recipeOptional.get();
        Set<IngredientsDTO> ingredientsDTOSet = new HashSet<>();
        recipe.getIngredients().forEach(ingredient -> {
            IngredientsDTO ingredientsDTO = ingredientMapper.ingredientsToIngredientsDTO(ingredient);
            ingredientsDTOSet.add(ingredientsDTO);
        });
        return ingredientsDTOSet;
    }

    @Override
    public NotesDTO getNotes(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        Recipe recipe = recipeOptional.get();
        Notes notes = recipe.getNotes();
        return new NotesDTO(notes.getId(), notes.getRecipeNotes());
    }

    @Override
    public void printRecipe(Recipe recipe) {
        List<Category> recipeCategories = categoryRepository.findAll();

        System.out.println("[ PRINTING DETAILS FOR RECIPE: " + recipe.getId() + " ]");
        System.out.println("\tDESCRIPTION: " + recipe.getDescription());
        System.out.println("\tPREP TIME: " + recipe.getPrepTime());
        System.out.println("\tCOOK TIME: " + recipe.getCookTime());
        System.out.println("\tSERVINGS: " + recipe.getServings());
        System.out.println("\tSOURCE: " + recipe.getSource());
        System.out.println("\tURL: " + recipe.getUrl());
        System.out.println("\tDIRECTIONS: " + recipe.getDirections());
        System.out.println("\tIMAGE: " + recipe.getImage().length);
        System.out.println("\tDIFFICULTY: " + recipe.getDifficulty());
        System.out.println("\tNOTES: " + recipe.getNotes().getRecipeNotes());

        Set<Category> finalList = new HashSet<>();
        recipeCategories.stream().forEach(x -> x.getReceipes().forEach(y -> {
            if (y.getId() == recipe.getId()) {
                finalList.add(x);
            }
        }));

        System.out.println("\tCATEGORIES: ");
        finalList.stream().forEach(category -> {
            System.out.println("\t\t" + category.getDescription() + "");
        });

        Set<Ingredient> recipeIngredients = recipe.getIngredients();
        System.out.println("\tINGREDIENTS: ");
        recipeIngredients.stream().forEach(ingredient -> {
            System.out.println("\t\tID: " + ingredient.getId() + ", INGREDIENT: " + ingredient.getDescription() + ", AMOUNT: " + ingredient.getAmount() + ", UOM: " + ingredient.getUom().getDescription());
        });

        System.out.println("*** [ END OF PRINTING DETAILS FOR RECIPE: " + recipe.getId() + " ] *** ");
    }
}
