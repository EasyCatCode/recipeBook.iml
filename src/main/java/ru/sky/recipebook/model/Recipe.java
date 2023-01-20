package ru.sky.recipebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    @NotBlank
    private String name;
    @Positive
    private Integer cookingTime;
    @NotEmpty
    private List<Ingredient> ingredients;
    private List<String> steps;
}
