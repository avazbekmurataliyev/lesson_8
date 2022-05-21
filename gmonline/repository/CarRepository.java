package uz.monopoliya.gmonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.monopoliya.gmonline.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    @Query(value = "select * from car c left outer join users u on c.autoshop_id=u.id where u.id=:id", nativeQuery = true)
    List<Car> findAllByCarList(Integer id);

    List<Car> findCarsByRegion_Id(Integer id);
}
