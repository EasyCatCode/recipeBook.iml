package ru.sky.recipebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sky.recipebook.exception.FileProcessingException;
import ru.sky.recipebook.model.Ingredient;
import ru.sky.recipebook.service.FileService;
import ru.sky.recipebook.service.IngredientService;
import ru.sky.recipebook.service.RecipeService;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
@Tag(name = "Files", description = "CRUD-операции для работы с файлами")
public class FilesController {

    private final FileService recipeFileService;
    private final FileService ingredientFileService;
    private final RecipeService recipeService;

    public FilesController(@Qualifier("ingredientFileService") FileService ingredientFileService,
                           @Qualifier("recipeFileService") FileService recipeFileService,
                           RecipeService recipeService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFileService = ingredientFileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ingredient/export")
    @Operation(description = "Экспорт файла ингредиентов")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws IOException {
        InputStreamResource inputStreamResource = ingredientFileService.exportFile();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"Ingredients.json\"")
                .body(inputStreamResource);
    }

    @PostMapping(value = "/ingredient/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла ингредиентов")
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile file) throws FileNotFoundException {
        ingredientFileService.importFile(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recipe/export")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadTxtRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportFile();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"Recipes.json\"")
                .body(inputStreamResource);
    }

    @GetMapping("/recipe/exporttxt")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportTxtFile(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(Files.size(recipeFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"AllRecipes.txt\"")
                .body(inputStreamResource);
    }


    @PostMapping(value = "/recipe/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла рецептов")
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile file) throws FileNotFoundException {
        recipeFileService.importFile(file);
        return ResponseEntity.ok().build();
    }

}
