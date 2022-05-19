package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Integer> {
}
