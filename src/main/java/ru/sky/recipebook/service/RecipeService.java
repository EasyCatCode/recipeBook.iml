package ru.sky.recipebook.service;

import ru.sky.recipebook.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);
}
