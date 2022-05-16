package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {

    List<District> findAllByRegion_Id(Integer id);


}
