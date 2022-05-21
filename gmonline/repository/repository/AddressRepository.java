package uz.monopoliya.gmonline.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.monopoliya.gmonline.entity.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query(value = "select * from address a left outer join district d on a.district_id=d.id where d.id=:id" , nativeQuery = true)
List<Address> findAllAdressFromDistrict(Integer id);

}
