package pl.pb.storageproject.serviceTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class CategoryServiceTests {

    private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());

    @Autowired
    private CategoryService categoryService;

    Category testcategory1 = new Category("testy");
    Category testcategory2 = new Category("przyklady");

    @Test
    public void addCategoryTest()
    {
        logger.info("CategoryServiceTest: " + "addCategoryTest");

        Category newcategory = categoryService.addCategory(testcategory1);
        System.out.println("PYTANIE1CAT: " + newcategory.getId());
        long xid = newcategory.getId();

        assert (categoryService.getCategoryById(xid).getId()).equals(newcategory.getId());
    }

    @Test
    public void findByKeyWordTest()
    {
        logger.info("CategoryServiceTest: " + "findByKeyWordTest");

        /*
        Category newcategory = categoryService.addCategory(testcategory1);
        String key = newcategory.getName();
        assert (categoryService.findByKeyWord(key).get(1).getId()).equals(newcategory.getId());
         */
    }

    @Test
    public void findByIdTest()
    {
        logger.info("CategoryServiceTest: " + "findByIdTest");

        Category newcategory = categoryService.addCategory(testcategory1);
        long xid = newcategory.getId();
        assert (categoryService.findById(xid).getId()).equals(newcategory.getId());
    }

    @Test
    public void getCategoryByIdTest()
    {
        logger.info("CategoryServiceTest: " + "getCategoryByIdTest");

        Category newcategory = categoryService.addCategory(testcategory1);
        long xid = newcategory.getId();
        assert (categoryService.getCategoryById(xid).getId()).equals(newcategory.getId());
    }

    @Test
    public void deleteCategoryByIdTest()
    {
        logger.info("CategoryServiceTest: " + "deleteCategoryByIdTest");

        Category newcategory = categoryService.addCategory(testcategory1);
        System.out.println("PYTANIE2CAT: " + newcategory.getId());
        long xid = newcategory.getId();
        categoryService.deleteCategoryById(xid);
        Category new1category = categoryService.addCategory(testcategory1);
        System.out.println("PYTANIE2CAT: " + new1category.getId());
        assert newcategory.getId().equals(xid);
    }


    @Test
    public void findMostPopularCategoryTest()
    {
        logger.info("CategoryServiceTest: " + "findMostPopularCategoryTest");
    }

    @Test
    public void getAllCategoriesTest()
    {
        logger.info("CategoryServiceTest: " + "getAllCategoriesTest");
        assert categoryService.getAllCategories() != null;
    }

}
