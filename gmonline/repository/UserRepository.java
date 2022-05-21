package uz.monopoliya.gmonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.monopoliya.gmonline.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {

}
