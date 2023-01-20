package ru.sky.recipebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sky.recipebook.model.Recipe;
import ru.sky.recipebook.service.RecipeService;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

//    public RecipeController(RecipeService recipeService) {
//        this.recipeService = recipeService;
//    }

    @GetMapping("/{id}")
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

}
