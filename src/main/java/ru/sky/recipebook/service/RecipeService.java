package ru.sky.recipebook.service;

import ru.sky.recipebook.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);

    Collection<Recipe> getAll();

    Recipe removeRecipe(int id);

    Recipe updateRecipe(int id, Recipe recipe);
}
