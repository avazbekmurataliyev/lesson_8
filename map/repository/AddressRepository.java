package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.avazbek.map.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(value = "select * from address a left outer join district d on a.district_id=d.id where d.id=:id" , nativeQuery = true)
List<Address> findAllAdressFromDistrict(Integer id);

}
