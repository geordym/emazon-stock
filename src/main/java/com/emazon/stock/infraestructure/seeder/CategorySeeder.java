package com.emazon.stock.infraestructure.seeder;

import com.emazon.stock.application.services.ICategoryService;
import com.emazon.stock.domain.model.Category;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class CategorySeeder implements CommandLineRunner {


    private final ICategoryService iCategoryService;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Set<String> uniqueCategories = new HashSet<>();
        Set<Category> categoriesToSave = new HashSet<>();

        while (categoriesToSave.size() < 20) {
            String departmentName = faker.commerce().department();
            if (uniqueCategories.add(departmentName)) {
                Category category = new Category(departmentName, "dessssssssssss");
                categoriesToSave.add(category);
            }
        }

        for (Category category : categoriesToSave) {
          //  iCategoryService.saveCategory(category);
        }

    }
}
