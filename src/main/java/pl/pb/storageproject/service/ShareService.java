package pl.pb.storageproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Share;
import pl.pb.storageproject.repository.ShareRepository;

import java.util.List;

@Service
public class ShareService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private ShareRepository shareRepository;

    public Share saveShare(Share share) {
        logger.info("ShareService: " + "saveShare");
        return shareRepository.save(share);
    }

    public List<Share> getAllSharedInformations() {
        logger.info("ShareService: " + "getAllSharedInformations");
        return shareRepository.findAll();
    }
}
