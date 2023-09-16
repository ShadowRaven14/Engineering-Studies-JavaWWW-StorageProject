package pl.pb.storageproject.serviceTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.exception.InformationNotExistException;
import pl.pb.storageproject.model.*;
import pl.pb.storageproject.service.CategoryService;
import pl.pb.storageproject.service.InformationService;
import pl.pb.storageproject.service.UserService;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class InformationServiceTests {

    private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());
    @Autowired private InformationService informationService;
    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;


    //Przygotowanie informacji
    /*
    private final Set<Role> roles = Set.of(new Role("FULL_USER"));

    User testuser = new User(9l, "User", "Testowy",
            "uuuutest", "haslo4", 23,
            "student@pb.edu.pl", roles);
    Category testcategory = new Category("testy");

     */

    @Test
    public void addInformationTest() throws MessagingException {
        logger.info("InformationServiceTest: " + "addInformationTest");
        /*
        Information testinformation = new Information();
        testinformation.setTitle("title");
        testinformation.setContent("content");
        testinformation.setDate(LocalDate.of(2022, 6, 1));
        testinformation.setCategory(new Category("testy"));
        testinformation.setUser(new User("User", "Testowy", "utest", "haslo1", 21, roles));


        testuser = userService.addUser(testuser);
        testcategory = categoryService.addCategory(testcategory);
        Information testinformation = new Information
                (1l,"title", "content", null, testcategory, testuser);
        testinformation = informationService.addInformation(testinformation);
        */
        //long xid1 = newiformation1.getId();
        //Information newiformation2 = informationService.addInformation(testinformation);
        //long xid2 = newiformation2.getId();
        //assert testinformation.getId().equals(newiformation1.getId());
    }

    @Test
    public void sortInformationsAscTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsAscTest");
        //assert informationService.sortInformationsAsc() != null;
    }

    @Test
    public void sortInformationsDescTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsDescTest");
        //assert informationService.sortInformationsDesc() != null;
    }

    @Test
    public void sortInformationsByCategoryAscTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsByCategoryAscTest");
        //assert informationService.sortInformationsAsc() != null;
    }

    @Test
    public void sortInformationsByCategoryDescTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsByCategoryDescTest");
        //assert informationService.sortInformationsByCategoryDesc() != null;
    }

    @Test
    public void sortInformationsByDateAscTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsByDateAscTest");
        //assert informationService.sortInformationsByDateAsc() != null;
    }

    @Test
    public void sortInformationsByDateDescTest()
    {
        logger.info("InformationServiceTest: " + "sortInformationsByDateDescTest");
        //assert informationService.sortInformationsByDateDesc() != null;
    }

    @Test
    public void filterInformationsByCategoryTest()
    {
        logger.info("InformationServiceTest: " + "filterInformationsByCategoryTest");
    }

    @Test
    public void filterInformationsByDateTest()
    {
        logger.info("InformationServiceTest: " + "filterInformationsByDateTest");
    }

    @Test
    public void getInforamationsTest()
    {
        logger.info("InformationServiceTest: " + "getInforamationsTest");
        //assert informationService.getInforamations() != null;
    }

    @Test
    public void getSharedInformationsTest()
    {
        logger.info("InformationServiceTest: " + "getSharedInformationsTest");
    }

    @Test
    public void deleteInformationTest() throws NoSuchFieldException
    {
        logger.info("InformationServiceTest: " + "deleteInformationTest");
    }

    @Test
    public void updateInformationTest() throws NoSuchFieldException
    {
        logger.info("InformationServiceTest: " + "updateInformationTest");
    }

    @Test
    public void getInformationByIdTest()
    {
        logger.info("InformationServiceTest: " + "getInformationByIdTest");
    }

    @Test
    public void findByKeyWordTest()
    {
        logger.info("InformationServiceTest: " + "findByKeyWord");
    }

}
