package pl.pb.storageproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pb.storageproject.model.Information;
import pl.pb.storageproject.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository< User, Long > {

    @Override
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findByLogin(String login);

    void deleteByLogin(String login);
}
