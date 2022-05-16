package uz.avazbek.map.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.avazbek.map.dto.DistrictDTO;
import uz.avazbek.map.entity.District;
import uz.avazbek.map.entity.Region;
import uz.avazbek.map.repository.DistrictRepository;
import uz.avazbek.map.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
O'zbekiston xaritasini ifodalovchi map jadvalini shakillantiring,
bunda  Region, District, Address class larini CRUD prinsiplari asosida yozing.
Region id orqali shu Region ga tegishli District larni olib keluvchi va
District id orqali shu District ga tegishli
Address larni olib keluvchi native query yozing yozing.
*/
@RestController
@RequestMapping("/region")
public class RegionController {
//Get all Address
@Autowired
private RegionRepository regionRepository;
@Autowired
private  DistrictRepository districtRepository ;

@GetMapping
    public List<Region> getAll(){
    return regionRepository.findAll();
}


@GetMapping("/{id}")
    public List<District> get(@PathVariable Integer id){
    Optional<Region> optionalRegion = regionRepository.findById(id);
    if (optionalRegion.isPresent()){
        return districtRepository.findAllByRegion_Id(id);
    }
    return new ArrayList<>();
}

@DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
    Optional<Region> optionalRegion = regionRepository.findById(id);
    if (optionalRegion.isPresent()){
        regionRepository.deleteById(id);
        return "Succes deleted Region";
    }
    else return "Region not found";


}


@PostMapping
    public String save(@RequestBody Region region){

    try {
            Region region1 = new Region();
            region1.setRegionName(region.getRegionName());
            regionRepository.save(region1);
            return "Save success ";
    }
    catch (Exception e) {

        return "Error";
    }
}

@PutMapping("/{id}")
    public String update(@PathVariable Integer id , @RequestBody Region region){

    Optional<Region> optionalRegion = regionRepository.findById(id);
    if (optionalRegion.isPresent()){
        Region region1 = optionalRegion.get();

        if (!region.getRegionName().isEmpty())
            region1.setRegionName(region.getRegionName());

        regionRepository.save(region1);
    return "Region update ";
    }
    else return "Region not found ";
}
}
