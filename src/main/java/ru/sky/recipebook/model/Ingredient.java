package ru.sky.recipebook.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Ingredient {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @Positive
    private Integer count;
    private String measureUnit;

}
