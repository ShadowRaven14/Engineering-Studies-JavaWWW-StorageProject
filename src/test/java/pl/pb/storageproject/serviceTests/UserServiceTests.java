package pl.pb.storageproject.serviceTests;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.repository.UserRepository;
import pl.pb.storageproject.service.UserService;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class UserServiceTests {

    private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());

    @Autowired
    private UserService userService;

    private final Set<Role> roles = Set.of(new Role("FULL_USER"));
    User testuser3 = new User(3l, "User", "Testowy", "utest", "haslo1", 21, "student@pb.edu.pl",roles);
    User testuser4 = new User(4l, "User", "Testowy", "uutest", "haslo2", 22, "student@pb.edu.pl",roles);
    User testuser5 = new User(5l, "User", "Testowy", "uuutest", "haslo3", 23, "student@pb.edu.pl",roles);
    User testuser6 = new User(6l, "User", "Testowy", "uuuutest", "haslo4", 23, "student@pb.edu.pl", roles);

    @Before
    public void addUserTestBefore() throws MessagingException {
        logger.info("UserServiceTest: " + "addUserTestBefore");
        System.out.println("BEFOREALL");

        User newuser3 = userService.addUser(testuser3);
        System.out.println("BEFOREALL: " + newuser3.getId());
        long xid3 = newuser3.getId();

        User newuser4 = userService.addUser(testuser4);
        System.out.println("BEFOREALL: " + newuser4.getId());
        long xid4 = newuser4.getId();

        User newuser5 = userService.addUser(testuser5);
        System.out.println("BEFOREALL: " + newuser5.getId());
        long xid5 = newuser5.getId();

        assert xid3 +1 == xid4;
        assert xid4 +1 == xid5;
        assert xid3 == xid5 -2;
        userService.deleteUser(xid3);
        userService.deleteUser(xid4);
        userService.deleteUser(xid5);
    }

    @Test
    public void addUserTest() throws MessagingException {
        logger.info("UserServiceTest: " + "addUserTest");

        User newuser = userService.addUser(testuser3);
        System.out.println("PYTANIE1: " + newuser.getId());
        long xid = newuser.getId();

        //System.out.println("TESTY: " + testuser1.getRoles());
        //System.out.println("TESTY: " + newuser.getRoles());
        //System.out.println("TESTY: " + testuser1.getPassword());
        //System.out.println("TESTY: " + newuser.getPassword());
        assert testuser3.equals(newuser);
    }

    @Test
    public void getUserByIdTest() throws MessagingException {
        logger.info("UserServiceTest: " + "getUserByIdTest");

        User newuser = userService.addUser(testuser4);
        System.out.println("PYTANIE2: " + newuser.getId());
        long xid = newuser.getId();

        //Optional<User> newnewuser = userService.getUserById(xid);
        //System.out.println("PYTANIE: " + newuser.getId());
        //System.out.println("PYTANIE: " + newuser.getRoles());
        //System.out.println("PYTANIE: " + (userService.getUserById(xid).get().getRoles()));
        //System.out.println("PYTANIE: " + (newuser).getRoles());
        //System.out.println("PYTANIE: " + (newnewuser).get().getRoles());
        assert (userService.getUserById(xid).get().getId()).equals(newuser.getId());
    }

    @Test
    public void getUserByLoginTest() throws MessagingException {
        logger.info("UserServiceTest: " + "getUserByLogin");

        /*
        User newuser = userService.addUser(testuser5);
        System.out.println("PYTANIE6: " + newuser.getId());
        String log = newuser.getLogin();

        assert (userService.getUserByLogin(log).getId()).equals(newuser.getId());
         */
    }

    @Test
    public void editUserTest() throws MessagingException {
        logger.info("UserServiceTest: " + "editUserTest");

        User newuser = userService.addUser(testuser5);
        System.out.println("PYTANIE3: " + newuser.getId());
        long xid = newuser.getId();

        User editeduser = newuser;
        editeduser.setLogin("edytowanylogin");
        userService.editUser(editeduser);

        //System.out.println("PYTANIE3: " + userService.getUserById(xid).get().getId());
        //System.out.println("PYTANIE3: " + userService.getUserById(xid).get().getLogin());
        assert (userService.getUserById(xid).get().getLogin()).equals("edytowanylogin");
    }

    @Test
    public void deleteUserTest() throws MessagingException {
        logger.info("UserServiceTest: " + "deleteUserTest");

        User newuser = userService.addUser(testuser6);
        System.out.println("PYTANIE4: " + newuser.getId());
        long xid = newuser.getId();
        userService.deleteUser(xid);
        User new1user = userService.addUser(testuser6);
        System.out.println("PYTANIE4: " + new1user.getId());
        assert new1user.getId().equals(xid);
        //userService.deleteUser((long)3);
        //userService.deleteUser((long)4);
        //userService.deleteUser((long)5);
        //System.out.println("PYTANIE4: " + userService.getUserById((long)2).get().getLogin());
    }

    @Test
    public void getUsersTest() {
        logger.info("UserServiceTest: " + "getUsers");
        assert userService.getUsers() != null;
    }

    @Test
    public void getUsersAscTest() {
        logger.info("UserServiceTest: " + "getUsersAsc");
        assert userService.getUsersAsc() != null;
    }

    @Test
    public void getUsersDescTest() {
        logger.info("UserServiceTest: " + "getUsersDesc");
        assert userService.getUsersDesc() != null;
    }

}
