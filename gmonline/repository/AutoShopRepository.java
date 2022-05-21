package uz.monopoliya.gmonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.monopoliya.gmonline.entity.AutoShop;
import uz.monopoliya.gmonline.entity.Car;

import java.util.List;

public interface AutoShopRepository extends JpaRepository<AutoShop,Integer> {

    List<AutoShop> findAllByGm_Id(Integer id);

}
