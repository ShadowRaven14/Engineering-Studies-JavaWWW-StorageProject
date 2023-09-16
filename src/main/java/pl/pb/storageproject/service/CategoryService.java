package pl.pb.storageproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Information;
import pl.pb.storageproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        logger.info("CategoryService: " + "addCategory");
        return categoryRepository.save(category);
    }

    public List<Category> findByKeyWord(String keyword) {
        logger.info("CategoryService: " + "findByKeyWord");
        return categoryRepository.findByKeyWord(keyword);
    }

    public Category findById(Long id) {
        logger.info("CategoryService: " + "findById");
        return categoryRepository.findById(id).get();
    }

    public Category getCategoryById(long id) {
        logger.info("CategoryService: " + "getCategoryById");

        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;

        if (optional.isPresent()) {
            category = optional.get();
        } else {
            logger.error("CategoryService: " + "Kategoria nie znaleziona dla szukanego id :: " + id);
            throw new RuntimeException("Kategoria nie znaleziona dla szukanego id :: " + id);
        }
        return category;
    }

    public void deleteCategoryById(long id) {
        logger.info("CategoryService: " + "deleteCategoryById");
        this.categoryRepository.deleteById(id);
    }

    public String findMostPopularCategory() {
        logger.info("CategoryService: " + "findMostPopularCategory");
        return categoryRepository.findMostPopularCategory();
    }

    public List<Category> getAllCategories() {
        logger.info("CategoryService: " + "getAllCategories");
        return categoryRepository.findAll();
    }
}
