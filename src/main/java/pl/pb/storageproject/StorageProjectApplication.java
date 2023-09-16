package pl.pb.storageproject;

//import org.slf4j.LoggerFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.service.UserService;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;

import java.util.Set;

@SpringBootApplication
@EnableAsync
@Log4j2
public class StorageProjectApplication {

    //private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());
    //Logger logger = LoggerFactory.getLogger(StorageProjectApplication.class);
    //private static Logger logger = LogManager.getLogger(StorageProjectApplication.class);
    public static void main(String[] args) throws IOException {

        //LogManager.getLogManager().readConfiguration(StorageProjectApplication.class.getResourceAsStream("/log4j2.properties"));
        //logger.config("./resources/log4j2-spring.xml");
        System.out.println("Start Aplikacji.");
        //logger.info("Start Aplikacji.");
        log.info("Start Aplikacji.");

        /*
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
         */

        SpringApplication.run(StorageProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserService userService) {

        //System.out.println("Ładuję podstawowe dane.");
        //logger.info("Ładuję podstawowe dane.");


        return (args) -> {
            userService.addAdminUser(new User(10l, "Admin" , "Admin", "admin", "admin", 18, "admin@pb.edu.pl", Set.of(new Role("ADMIN"))));
            userService.addAdminUser(new User(20l, "Test", "Test", "test", "test", 20, "test@pb.edu.pl", Set.of(new Role("ADMIN"))));
            userService.addAdminUser(new User(30l, "Student", "Student", "student", "student", 20, "student@pb.edu.pl", Set.of(new Role("ADMIN"))));
        };
    }
}
