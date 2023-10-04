package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    List<City> findAllByNameStartingWithIgnoreCase(String prefix);

    List<City> findAllByOrderByNameAsc();
    // Добавляет ORDER  import org.springframework.data.domain.Sort;
    //var users = userRepository.findAll(Sort.by(Sort.Order.asc("name")));


    // END
}
