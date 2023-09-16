package pl.pb.storageproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class StorageProjectApplicationTests {

    @Test
    void contextLoads() {

    }

}
