package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.avazbek.map.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {
    @Query(value = "select * from district d left outer join region r on d.region_id=r.id where r.id=:id" , nativeQuery = true)
    List<District> findAllDistrictFromRegion(Integer id);


}
