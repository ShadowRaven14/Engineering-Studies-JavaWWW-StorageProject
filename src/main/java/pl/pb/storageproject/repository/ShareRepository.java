package pl.pb.storageproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pb.storageproject.model.Share;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository< Share, Long > {

    @Override
    List<Share> findAll();
}
