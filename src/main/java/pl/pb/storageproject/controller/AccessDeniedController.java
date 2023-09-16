package pl.pb.storageproject.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pb.storageproject.model.User;

@Controller
public class AccessDeniedController {

    private final Logger logger = LogManager.getLogger(getClass());

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        //System.out.println("authentication.getCredentials(): " + authentication.getCredentials().toString());
        logger.trace("AccessDeniedController: " + "GET" + "/access-denied");
        return "/error/accessDenied";
    }
}
