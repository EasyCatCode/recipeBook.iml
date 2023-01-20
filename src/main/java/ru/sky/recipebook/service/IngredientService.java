package ru.sky.recipebook.service;

import ru.sky.recipebook.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer id);
}
