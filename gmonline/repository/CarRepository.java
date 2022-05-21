package uz.monopoliya.gmonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.monopoliya.gmonline.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    @Query(value = "select * from car c left outer join autoshop a on c.autoshop_id=a.id where a.id=:id", nativeQuery = true)
    List<Car> findAllByCarList(Integer id);

}
