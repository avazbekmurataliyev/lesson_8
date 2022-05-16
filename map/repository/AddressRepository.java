package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
List<Address> findAllByDistrict_Id(Integer id);

}
