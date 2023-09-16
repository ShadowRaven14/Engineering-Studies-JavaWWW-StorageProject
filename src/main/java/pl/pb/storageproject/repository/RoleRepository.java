package pl.pb.storageproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pb.storageproject.model.Role;

@Repository
public interface RoleRepository extends JpaRepository< Role, Long > {
}
