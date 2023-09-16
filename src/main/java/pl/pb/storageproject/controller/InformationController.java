package pl.pb.storageproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Information;
import pl.pb.storageproject.model.Share;
import pl.pb.storageproject.model.User;
import pl.pb.storageproject.service.CategoryService;
import pl.pb.storageproject.service.InformationService;
import pl.pb.storageproject.service.ShareService;
import pl.pb.storageproject.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InformationController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShareService shareService;


    private String datee = LocalDate.now().toString();
    //Wyswietlanie informacji
    @GetMapping("/informations")
    public String viewInformationsPage(Model model, String keyword, Principal principal, Long cat) {
        User userByLogin = userService.getUserByLogin(principal.getName());
        System.out.println(categoryService.findMostPopularCategory());
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        List <Information> informationList = informationService.getInforamations();
        List <Information> userInformationList = new LinkedList<Information>();

        for (int i = 0; i < informationList.size(); i++) {
            if (informationList.get(i).getUser().getId().equals(userByLogin.getId())) {
                userInformationList.add(informationList.get(i));
            }
        }

        if(keyword != null) {
            model.addAttribute("listInformations", informationService.findByKeyWord(keyword));
        } else {
            model.addAttribute("listInformations", userInformationList);
        }
        if(datee != null && cat != null){

            model.addAttribute("listInformations", informationService.filterInformationsByDateAndCategory(LocalDate.parse(datee),categoryService.findById(cat)));
        }
        if(datee != null) {
            model.addAttribute("listInformations", informationService.filterInformationsByDate(LocalDate.parse(datee)));
        } else {
            model.addAttribute("listInformations", informationService.filterInformationsByCategory(categoryService.findById(cat)));
        }

        if(cat != null) {
            model.addAttribute("listInformations", informationService.filterInformationsByCategory(categoryService.findById(cat)));
        } else {
            model.addAttribute("listInformations", informationService.filterInformationsByDate(LocalDate.parse(datee)));
        }

        model.addAttribute("listCategories", categoryService.getAllCategories());

        logger.trace("InformationController: " + "GET" + "/informations");
        return "/information/informations";
    }


    //Wyswietlanie udostepnionych informacji
    @GetMapping("/sharedInformation")
    public String viewSharedInformations(Model model, Principal principal) {
        User userByLogin = userService.getUserByLogin(principal.getName());

        Share share = new Share();

        List<Share> shareList = informationService.getSharedInformations();
        List<Share> userSharedList = new LinkedList<Share>();

        for (int i = 0; i < shareList.size(); i++) {
            if (shareList.get(i).getIdTo().equals(userByLogin.getId())) {
                    userSharedList.add(shareList.get(i));
            }
        }
        List<Information> collect = userSharedList.stream().map(Share::getInformation).collect(Collectors.toList());
        model.addAttribute("listInformations", collect);

        logger.trace("InformationController: " + "GET" + "/sharedInformation");
        return "/information/sharedInformations";
    }


    //Tworzenie nowych informacji
    @GetMapping("/newInformation")
    public String newInformationForm(Model model) {
        Information information = new Information();
        model.addAttribute("information", information);
        model.addAttribute("listCategories", categoryService.getAllCategories());

        logger.trace("InformationController: " + "GET" + "/newInformation");
        return "/information/newInformation";
    }

    @PostMapping("/saveInformation")
    public String saveInformation(@Valid @ModelAttribute("information") Information information, BindingResult result, Model model, Principal principal) {
        User userByLogin = userService.getUserByLogin(principal.getName());

        if (result.hasErrors()) {
            model.addAttribute("listCategories", categoryService.getAllCategories());
            logger.error("InformationController: " + "POST" + "/saveInformation");
            return "/information/newInformation";
        }
        information.setUser(userByLogin);

        informationService.addInformation(information);

        logger.trace("InformationController: " + "POST" + "/saveInformation");
        return "redirect:/informations";
    }


    // Update informacji
    @GetMapping("/updateInformation/{id}")
    public String updateInformation(@PathVariable(value = "id") long id, Model model) {
        Information information = informationService.getInformationById(id);
        model.addAttribute("listCategories", categoryService.getAllCategories());
        model.addAttribute("information", information);

        logger.trace("InformationController: " + "GET" + "/updateInformation/{id}");
        return "/information/updateInformation";
    }

    @PostMapping("/saveInformationUpdate")
    public String saveInformationUpdate(@Valid @ModelAttribute("information") Information information, BindingResult result, Model model, Principal principal) {
        System.out.println("UPDATE:" + userService.getUserByLogin(principal.getName()));
        User userByLogin = userService.getUserByLogin(principal.getName());

        if (result.hasErrors()) {
            model.addAttribute("listCategories", categoryService.getAllCategories());
            logger.error("InformationController: " + "POST" + "/saveInformationUpdate");
            return "/information/updateInformation";
        }

        information.setUser(userByLogin);

        informationService.addInformation(information);

        logger.trace("InformationController: " + "POST" + "/saveInformationUpdate");
        return "redirect:/informations";
    }


    //Usuwanie informacji
    @GetMapping("deleteInformation/{id}")
    public String deleteInformation(@PathVariable(value = "id") long id) throws NoSuchFieldException {
        this.informationService.deleteInformation(id);

        logger.trace("InformationController: " + "GET" + "deleteInformation/{id}");
        return "redirect:/informations";
    }



    //sortowanie
    public List<Information> sortowanie(List<Information> informationList, Principal principal) {
        User userByLogin = userService.getUserByLogin(principal.getName());
        List <Information> userInformationList = new LinkedList<Information>();

        for (int i = 0; i < informationList.size(); i++) {
            if (informationList.get(i).getUser().getId().equals(userByLogin.getId())) {
                userInformationList.add(informationList.get(i));
            }
        }
        logger.trace("InformationController: " + "GET" + "/informationSort");
        return userInformationList;
    }




    @GetMapping("/informationsSortedAsc")
    public String informationsSortedAsc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsAsc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }

    @GetMapping("/informationsSortedDesc")
    public String informationsSortedDesc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsDesc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }

    @GetMapping("/informationsSortedByDateAsc")
    public String informationsSortedByDateAsc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsByDateAsc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }

    @GetMapping("/informationsSortedByDateDesc")
    public String informationsSortedByDateDesc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsByDateDesc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }

    @GetMapping("/informationsSortedByCategoryAsc")
    public String informationsSortedByCategoryAsc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsByCategoryAsc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }

    @GetMapping("/informationsSortedByCategoryDesc")
    public String informationsSortedByCategoryDesc(Model model, Principal principal) {
        List <Information> informationList = informationService.sortInformationsByCategoryDesc();
        model.addAttribute("listInformations", sortowanie(informationList, principal));
        model.addAttribute("datee",datee);
        model.addAttribute("cate",categoryService.findMostPopularCategory());
        model.addAttribute("listCategories", categoryService.getAllCategories());

        return "/information/informations";
    }


    // Udostepnianie Informacji
    @GetMapping("shareInformation/{id}")
    public String shareInformation(@PathVariable(value = "id") long id, Model model,  Principal principal) {
        User userByLogin = userService.getUserByLogin(principal.getName());
        Information information = informationService.getInformationById(id);

        List <User> userList = userService.getUsersAsc();
        List <User> userFinalList = new LinkedList<User>();

        for (int i = 0; i < userList.size(); i++) {
            if (!userList.get(i).getId().equals(userByLogin.getId())) {userFinalList.add(userList.get(i));}
        }

        model.addAttribute("information", information);
        model.addAttribute("listUsers", userFinalList);

        logger.trace("InformationController: " + "GET" + "shareInformation/{id}");
        return "/information/shareInformation";
    }

    @PostMapping("/shareInformation")
    public String shareInformationPost(@ModelAttribute("information") Information information, Principal principal){
        System.out.println(information.getUser().getId());
        shareService.saveShare(new Share(1L,information.getUser().getId(),information));
        logger.trace("InformationController: " + "POST" + "/shareInformation");
        return "redirect:/informations";
    }

    @GetMapping("/filterDate")
    public String filterByDate(@RequestParam LocalDate localDate){
        informationService.filterInformationsByDate(localDate);
        logger.trace("InformationController: " + "GET" + "/filterDate");
        return "redirect:/informations";
    }

    @GetMapping("/filterCategory")
    public String filterByCategory(@RequestParam Category category){
        informationService.filterInformationsByCategory(category);
        logger.trace("InformationController: " + "GET" + "/filterCategory");
        return "redirect:/informations";
    }
}
