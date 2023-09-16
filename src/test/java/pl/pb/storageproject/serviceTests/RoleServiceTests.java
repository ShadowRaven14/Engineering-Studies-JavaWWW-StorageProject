package pl.pb.storageproject.serviceTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pb.storageproject.StorageProjectApplication;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.service.RoleService;

import java.util.List;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class RoleServiceTests {

    private static Logger logger = Logger.getLogger(StorageProjectApplication.class.getName());
    @Autowired
    private RoleService roleService;

    Role testrole = new Role("TESTOWA");

    @Test
    public void saveRoleTest()
    {
        logger.info("RoleServiceTest: " + "saveRoleTest");
        roleService.saveRole(testrole);
    }

    @Test
    public void editRoleTest()
    {
        logger.info("RoleServiceTest: " + "editRoleTest");
        Role newrole = new Role("TESTOWA_EDIT");
        roleService.editRole((long)1, newrole);
    }

    @Test
    public void getAllRolesTest()
    {
        logger.info("RoleServiceTest: " + "getAllRolesTest");
        assert roleService.getAllRoles() != null;
    }

}
