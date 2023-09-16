package pl.pb.storageproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {

    private final Logger logger = LogManager.getLogger(getClass());
    //private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private User userKox = new User();



//    @Autowired
//    private pl.macieksob.rentCar.service.MailService mailService;

    private final Set<Role> roles = new HashSet<Role>(Set.of(new Role("ADMIN"),
            new Role("LIMITED_USER"), new Role("FULL_USER")));


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }


    @GetMapping("/index")
    public String homePage() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        logger.trace("UserController: " + "GET" + "/index");
        return "/index";
    }


    @GetMapping("/helloUser")
    public String helloUserPage() {
        logger.trace("UserController: " + "GET" + "/helloUser");
        return "/user/helloUser";
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        logger.trace("UserController: " + "GET" + "/register");
        return "/user/register";
    }


    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) throws MessagingException {
        if (result.hasErrors()) {
            logger.error("UserController: " + "POST" + "/registerUser");
            return "/user/register";
        }

        userService.addUser(user);
//        mailService.sendMail();
        logger.trace("UserController: " + "POST" + "/registerUser");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage() {
        logger.trace("UserController: " + "GET" + "/login");
        return "/user/login";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute( "loginError", true);
        logger.trace("UserController: " + "REQUEST" + "/login-error");
        return "/user/login";
    }


    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("listUsers", userService.getUsers());
        logger.trace("UserController: " + "GET" + "/users");
        return "/user/users";
    }


    //Aktualizacja użytkownika
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id") long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.trace("UserController: " + "GET" + "/updateUser/{id}");
        return "/user/updateUser";
    }


    @PostMapping("/saveUserUpdate")
    public String saveUserUpdate(@Valid @ModelAttribute("user") User user, BindingResult result) throws MessagingException {
        if (result.hasErrors()) {
            logger.error("UserController: " + "POST" + "/saveUserUpdate");
            return "/user/updateUser";
        }

        System.out.println("user.getLogin(): " + user.getLogin());
        System.out.println("user.getPassword(): " + user.getPassword());
        userService.addUser(user);
        logger.trace("UserController: " + "POST" + "/saveUserUpdate");
        return "redirect:/users";
    }

    String tempRole = "";

    //Zmiana uprawnien uzytkownika
    @GetMapping("/changeUserRole/{id}")
    public String changeUserRoleForm(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getUserById(id).orElseThrow();

        Role role = user.getRoles().stream().findFirst().orElseThrow();
        roles.removeIf(role1 -> role1.getName().equals(role.getName()));

        tempRole = role.getName();

        userKox.setId(user.getId());
        userKox.setName(user.getName());
        userKox.setPassword(user.getPassword());
        userKox.setLogin(user.getLogin());
        userKox.setAge(user.getAge());
        userKox.setRoles(user.getRoles());
        userKox.setSurname(user.getSurname());

        model.addAttribute("user", user);
        model.addAttribute("role", new Role());
        model.addAttribute("roleList", roles);

        logger.trace("UserController: " + "GET" + "/changeUserRole/{id}");
        return "/user/changeUserRole";
    }

    
    @PostMapping("/changeUserRole")
    public String changeUserRole( @ModelAttribute("role") Role role, @ModelAttribute("user") User user) {
        user.setId(userKox.getId());
        user.setName(userKox.getName());
        user.setPassword(userKox.getPassword());
        user.setLogin(userKox.getLogin());
        user.setAge(userKox.getAge());
        user.setSurname(userKox.getSurname());
        user.setRoles(Set.of(role));

        userService.editUser(user);
        roles.add(new Role(tempRole));

        logger.trace("UserController: " + "POST" + "/changeUserRole");
        return "redirect:/users";
    }

    //Usuwanie uzytkownikow
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id)  {
        this.userService.deleteUser(id);
        logger.trace("UserController: " + "GET" + "/deleteUser/{id}");
        return "redirect:/users";
    }
}