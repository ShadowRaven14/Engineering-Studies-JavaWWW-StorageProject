package pl.pb.storageproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pb.storageproject.exception.UserDuplicateException;
import pl.pb.storageproject.exception.UserNotExistException;
import pl.pb.storageproject.model.Information;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.repository.UserRepository;
import pl.pb.storageproject.security.CustomUserDetailsService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MailService mailService;


    public User addUser(User user) throws MessagingException {
        logger.info("UserService: " + "addUser");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(new Role("LIMITED_USER")));
        mailService.sendMail(user.getEmail(), "<b>Rejestracja przebirgła pomyslnie!</b>","Potwierdzenie rejestracji",true);
        userRepository.save(user);

        return user;
    }

    public User addAdminUser(User user) {
        logger.info("UserService: " + "addAdmin");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(new Role("ADMIN")));

        userRepository.save(user);

        return user;
    }

    public Optional<User> getUserById(long id) {
        logger.info("UserService: " + "getUserById");
        return userRepository.findById(id);
    }


    public User getUserByLogin(String login) {
        logger.info("UserService: " + "getUserByLogin");
        return userRepository.findByLogin(login);
    }

    public User editUser(User user) {
        logger.info("UserService: " + "editUser");
        userRepository.save(user);

        return user;
    }

    public void editUser(Long id, User newUser) throws NoSuchFieldException {
        logger.info("UserService: " + "editUser");

        if (userRepository.findById(id).isEmpty()) {
            logger.error("UserService: " + "User not found");
            throw new UserNotExistException("User not found");
        }

        User user = userRepository.findById(id).get();
        user.setName(newUser.getName());
        user.setLogin(newUser.getLogin());
        user.setId(id);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        logger.info("UserService: " + "deleteUser");
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        logger.info("UserService: " + "getUsers");
        return userRepository.findAll();
    }

    public  List<User> getUsersAsc() {
        logger.info("UserService: " + "getUsersAsc");
        return userRepository.findAll(Sort.by(Sort.Order.asc("login")));
    }

    public  List<User> getUsersDesc() {
        logger.info("UserService: " + "getUsersDesc");
        return userRepository.findAll(Sort.by(Sort.Order.desc("login")));
    }
}
