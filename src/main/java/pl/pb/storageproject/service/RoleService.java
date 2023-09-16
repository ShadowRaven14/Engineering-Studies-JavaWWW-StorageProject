package pl.pb.storageproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.repository.CategoryRepository;
import pl.pb.storageproject.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public void editRole(Long id, Role role){
        Role role1 = roleRepository.findById(id).get();
        role1.setId(id);
        role1.setName(role.getName());
        role1.setUsers(role.getUsers());
        roleRepository.save(role);
    }

    public List< Role > getAllRoles() {
        logger.info("RoleService: " + "getAllRoles");
        return roleRepository.findAll();
    }
}
