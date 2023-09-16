package pl.pb.storageproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Information;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository< Information, Long > {

    @Override
    List< Information > findAll();

    List< Information > findAlLByDate(LocalDate date);

    List< Information > findAllByCategory(Category category);


    @Query(value = "select * from INFORMATION i inner join CATEGORY C on C.ID = i.CATEGORY_ID where i.TITLE iLIKE  %:keyword%  or i.CONTENT iLIKE  %:keyword% or c.name iLIKE  %:keyword% or i.date iLIKE  %:keyword%" , nativeQuery = true)
    List< Information > findByKeyWord(@Param("keyword") String keyword);

    List<Information> findAllByDateAndCategory(LocalDate parse, Category category);
}
