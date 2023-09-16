package pl.pb.storageproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pb.storageproject.model.Category;
import pl.pb.storageproject.model.Information;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository< Category, Long > {

    @Query(value = "select * from CATEGORY c where c.name iLIKE  %:keyword% " , nativeQuery = true)
    List<Category> findByKeyWord(@Param("keyword") String keyword);

    Category findAllByName(String name);

    @Query(nativeQuery = true,value = "SELECT name\n" +
            "FROM (SELECT name, COUNT(name)  AS name_count\n" +
            "FROM category\n" +
            "GROUP BY name\n" +
            "ORDER BY name DESC )\n" +
            "GROUP BY name limit 1;")
    String findMostPopularCategory();
}
