package uz.monopoliya.gmonline.controller.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Car;
import uz.monopoliya.gmonline.entity.entity.District;
import uz.monopoliya.gmonline.entity.entity.Region;
import uz.monopoliya.gmonline.repository.CarRepository;
import uz.monopoliya.gmonline.repository.repository.DistrictRepository;
import uz.monopoliya.gmonline.repository.repository.RegionRepository;

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
private DistrictRepository districtRepository ;

@Autowired
private CarRepository carRepository ;
@GetMapping
    public List<Region> getAll(){
    return regionRepository.findAll();
}


@GetMapping("/{id}")
    public List<Car> get(@PathVariable Integer id){
    Optional<Region> optionalRegion = regionRepository.findById(id);
    if (optionalRegion.isPresent()){
        return carRepository.findCarsByRegion_Id(id);
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
