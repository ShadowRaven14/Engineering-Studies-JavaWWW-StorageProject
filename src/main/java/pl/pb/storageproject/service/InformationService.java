package pl.pb.storageproject.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pb.storageproject.exception.InformationNotExistException;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Information;
import pl.pb.storageproject.model.Share;
import pl.pb.storageproject.repository.InformationRepository;
import pl.pb.storageproject.repository.ShareRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class InformationService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private ShareRepository shareRepository;


    public Information addInformation(Information information) {
        logger.info("InformationService: " + "addInformation");
        information.setDate(LocalDate.now());
        informationRepository.save(information);
        return information;
    }


    public java.util.List< Information > sortInformationsAsc() {
        logger.info("InformationService: " + "sortInformationsAsc");
        return informationRepository.findAll(Sort.by(Sort.Order.asc("title")));
    }

    public java.util.List< Information > sortInformationsDesc() {
        logger.info("InformationService: " + "sortInformationsDesc");
        return informationRepository.findAll(Sort.by(Sort.Order.desc("title")));
    }

    public java.util.List< Information > sortInformationsByCategoryAsc() {
        logger.info("InformationService: " + "sortInformationsByCategoryAsc");
        return informationRepository.findAll(Sort.by(Sort.Order.asc("category")));
    }

    public java.util.List< Information > sortInformationsByCategoryDesc() {
        logger.info("InformationService: " + "sortInformationsByCategoryDesc");
        return informationRepository.findAll(Sort.by(Sort.Order.desc("category")));
    }

    public java.util.List< Information > sortInformationsByDateAsc() {
        logger.info("InformationService: " + "sortInformationsByDateAsc");
        return informationRepository.findAll(Sort.by(Sort.Order.asc("date")));
    }
    public java.util.List< Information > sortInformationsByDateDesc() {
        logger.info("InformationService: " + "sortInformationsByDateDesc");
        return informationRepository.findAll(Sort.by(Sort.Order.desc("date")));
    }

    public java.util.List< Information > filterInformationsByCategory(Category category) {
        logger.info("InformationService: " + "filterInformationsByCategory");
        return informationRepository.findAllByCategory(category);
    }

    public java.util.List< Information > filterInformationsByDate(LocalDate date) {
        logger.info("InformationService: " + "filterInformationsByDate");
        return informationRepository.findAlLByDate(date);
    }

    public java.util.List< Information > getInforamations() {
        logger.info("InformationService: " + "getInforamations");
        return informationRepository.findAll();
    }

    public java.util.List< Share > getSharedInformations() {
        logger.info("InformationService: " + "getSharedInformations");
        return shareRepository.findAll();
    }


    public void deleteInformation(Long id) throws NoSuchFieldException {
        logger.info("InformationService: " + "deleteInformation");

        if (informationRepository.findById(id).isEmpty()) {
            logger.error("InformationService: " + "Information not found!");
            throw new InformationNotExistException("Information not found!");
        }
        informationRepository.deleteById(id);
    }

    public void updateInformation(Long id, Information newInformation) throws NoSuchFieldException {
        logger.info("InformationService: " + "updateInformation");

        if (informationRepository.findById(id).isEmpty()) {
            logger.error("InformationService: " + "Information not found!");
            throw new InformationNotExistException("Information not found!");
        }

        Information information = informationRepository.findById(id).get();
        information.setCategory(newInformation.getCategory());
        information.setTitle(newInformation.getTitle());
        information.setId(id);

        informationRepository.save(information);
    }

    public Information getInformationById(long id) {
        logger.info("InformationService: " + "getInformationById");

        Optional<Information> optional = informationRepository.findById(id);
        Information information = null;

        if (optional.isPresent()) {
            information = optional.get();
        } else {
            logger.error("InformationService: " + "Information not found!");
            throw new InformationNotExistException("Information not found!");
        }
        return information;
    }


    public List<Information> findByKeyWord(String keyword) {
        logger.info("InformationService: " + "findByKeyWord");
        return informationRepository.findByKeyWord(keyword);
    }

    public List<Information> filterInformationsByDateAndCategory(LocalDate parse,Category category) {
        return informationRepository.findAllByDateAndCategory(parse,category);
    }
}
