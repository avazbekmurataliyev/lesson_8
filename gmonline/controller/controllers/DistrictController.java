    package uz.monopoliya.gmonline.controller.controllers;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.monopoliya.gmonline.payload.DistrictDTO;
    import uz.monopoliya.gmonline.entity.entity.Address;
    import uz.monopoliya.gmonline.entity.entity.District;
    import uz.monopoliya.gmonline.entity.entity.Region;
    import uz.monopoliya.gmonline.repository.repository.AddressRepository;
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
    @RequestMapping("/district")
    public class DistrictController {
    //Get all Address
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private AddressRepository addressRepository ;
    @GetMapping
        public List<District> getAll(){
        return districtRepository.findAll();
    }
    @GetMapping("/{id}")
        public List<Address> get(@PathVariable Integer id){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent()){
            return addressRepository.findAllAdressFromDistrict(id);
        }
        return new ArrayList<>();
    }


    @DeleteMapping("/{id}")
        public String delete(@PathVariable Integer id){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent()){
            districtRepository.deleteById(id);
            return "Succes deleted District";
        }
        else return "District not found";


    }


    @PostMapping
        public String save(@RequestBody DistrictDTO districtDTO){

        try {
            Optional<Region> optionalRegion = regionRepository.findById(districtDTO.getRegionId());
            if (optionalRegion.isPresent()) {
                District district = new District();
                district.setDistrictName(districtDTO.getDistrictName());
                district.setRegion(optionalRegion.get());
                districtRepository.save(district);
                return "Save success ";
            }
            else return "This region id not found ";
        }
        catch (Exception e) {

            return "Error";
        }
    }

    @PutMapping("/{id}")
        public String update(@PathVariable Integer id , @RequestBody DistrictDTO districtDTO){

        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent()){
            District district = optionalDistrict.get();
            if (districtDTO.getRegionId()>0){
                Optional<Region> optionalRegion = regionRepository.findById(districtDTO.getRegionId());
                if (optionalRegion.isPresent()){
                    district.setRegion(optionalRegion.get());
                }
            }
            if (!districtDTO.getDistrictName().isEmpty())
                district.setDistrictName(districtDTO.getDistrictName());

            districtRepository.save(district);
        return "District update ";
        }
        else return "District not found ";
    }
    }
