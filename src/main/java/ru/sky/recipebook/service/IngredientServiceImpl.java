package ru.sky.recipebook.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sky.recipebook.exception.IngredientExistsException;
import ru.sky.recipebook.model.Ingredient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredientMap.containsValue(ingredient)) {
            throw new IngredientExistsException();
        }
        ingredientMap.put(id++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id Не найден");
        }
        return ingredientMap.get(id);
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }

    @Override
    public Ingredient removeIngredient(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id Не найден");
        }
        return ingredientMap.remove(id);
    }


    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id Не найден");
        }
        ingredientMap.put(id, ingredient);
        return ingredient;
    }


}
