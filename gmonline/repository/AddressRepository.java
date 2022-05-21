package uz.monopoliya.gmonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.monopoliya.gmonline.entity.Address;

public interface AddressRepository extends JpaRepository<Address , Integer> {

}
