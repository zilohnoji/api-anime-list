package com.donatoordep.anime_list_api.services.business.rules.anime.create.validation;

import com.donatoordep.anime_list_api.dto.request.CategoriesRequestDTO;
import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.enums.Category;
import com.donatoordep.anime_list_api.repositories.CategoriesRepository;
import com.donatoordep.anime_list_api.services.business.rules.anime.create.CreateAnimeArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.create.CreateAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.InvalidEnumValueException;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CategoryNotExistsValidation implements CreateAnimeValidation {

    @Autowired
    CategoriesRepository repository;

    @Override
    public void validation(CreateAnimeArgs args) {
        List<Categories> all = repository.findAll();
        List<Category> categoryList = all.stream().map(Categories::getCategory).toList();

        List<String> invalidCategories = args.dto().getCategories().stream()
                .map(CategoriesRequestDTO::getCategory)
                .filter(category -> all.stream().noneMatch(c -> c.getCategory().name().equals(category)))
                .toList();

        boolean categoryNotExists = args.dto().getCategories().stream()
                .noneMatch(category -> all.stream()
                        .anyMatch(c -> c.getCategory().name().equals(category.getCategory())));

        if (categoryNotExists) {
            throw new InvalidEnumValueException(invalidCategories.toString(), Category.class.toString(),
                    ConvertingType.listToString(categoryList));
        }
    }
}