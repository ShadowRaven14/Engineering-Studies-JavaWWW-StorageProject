package pl.pb.storageproject.serviceTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.model.Share;
import pl.pb.storageproject.service.ShareService;

import java.util.List;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class ShareServiceTests {

    private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());

    //Share share =

    @Autowired
    private ShareService shareService;

    @Test
    public void saveShareTest()
    {
        logger.info("ShareServiceTest: " + "saveShareTest");
    }

    @Test
    public void getAllSharedInformationsTest()
    {
        logger.info("ShareServiceTest: " + "getAllSharedInformations");
        assert shareService.getAllSharedInformations() != null;
    }

}
