package pl.pb.storageproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.service.CategoryService;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String viewCategoriesPage(Model model, String keyword) {

        if(keyword != null) {
            model.addAttribute("listCategories", categoryService.findByKeyWord(keyword));
        } else {
            model.addAttribute("listCategories", categoryService.getAllCategories());
        }

        logger.trace("CategoryController: " + "GET" + "/categories");
        return "/category/categories";
    }

    @GetMapping("/newCategory")
    public String newCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);

        logger.trace("CategoryController: " + "GET" + "/newCategory");
        return "/category/newCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("CategoryController: " + "GET" + "/saveCategory");
            return "/category/newCategory";
        }
        categoryService.addCategory(category);
        logger.trace("CategoryController: " + "GET" + "/saveCategory");
        return "redirect:/categories";
    }


    @GetMapping("updateCategory/{id}")
    public String updateCategoryForm(@PathVariable (value = "id") long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        logger.trace("CategoryController: " + "GET" + "updateCategory/{id}");
        return "/category/updateCategory";
    }

    @GetMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable(value = "id") long id) {
        this.categoryService.deleteCategoryById(id);
        logger.trace("CategoryController: " + "GET" + "deleteCategory/{id}");
        return "redirect:/categories";
    }
}