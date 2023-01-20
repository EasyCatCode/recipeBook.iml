package ru.sky.recipebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.sky.recipebook.model.Ingredient;
import ru.sky.recipebook.model.Recipe;
import ru.sky.recipebook.service.IngredientService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(
            description = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ингредиент  найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Добавление ингредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    })
    @PostMapping
    ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient) {
        //todo: delete comment
//        if (StringUtils.isBlank(ingredient.getName())) {
//            return ResponseEntity.badRequest().body("Название ингредиента не может быть пустым");
//        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @GetMapping
    @Operation(summary = "Получение всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты получены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    })
    public Collection<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален"
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Ingredient> removeIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.removeIngredient(id));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
