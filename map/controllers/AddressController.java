package uz.avazbek.map.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.avazbek.map.dto.AddressDTO;
import uz.avazbek.map.entity.Address;
import uz.avazbek.map.entity.District;
import uz.avazbek.map.repository.AddressRepository;
import uz.avazbek.map.repository.DistrictRepository;

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
@RequestMapping("/address")
public class AddressController {
    //Get all Address
    @Autowired
   private AddressRepository addressRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @GetMapping
        public List<Address> getAddresses(){
        return addressRepository.findAll();
    }
    @GetMapping("/{id}")
        public Address getAddress(@PathVariable Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            return optionalAddress.get();
        }
        return new Address();
    }

    @DeleteMapping("/{id}")
        public String delete(@PathVariable Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.deleteById(id);
            return "Succes deleted Address";
        }
        else return "Address not found";


    }


    @PostMapping
        public String save(@RequestBody AddressDTO addressDTO){

        try {
            Optional<District> optionalDistrict = districtRepository.findById(addressDTO.getDistrictId());
            if (optionalDistrict.isPresent()) {
                Address address = new Address();
                address.setHomeNumber(addressDTO.getHomeNumber());
                address.setStreetName(addressDTO.getStreetName());
                address.setDistrict(optionalDistrict.get());
                addressRepository.save(address);
                return "Save success ";
            }
            else return "This district id not found ";
        }
        catch (Exception e) {

            return "Error";
        }
    }

    @PutMapping("/{id}")
        public String update(@PathVariable Long id , @RequestBody AddressDTO addressDTO){

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            if (addressDTO.getDistrictId()>0){
                Optional<District> optionalDistrict = districtRepository.findById(addressDTO.getDistrictId());
                if (optionalDistrict.isPresent()){
                    address.setDistrict(optionalDistrict.get());
                }
            }
            if (!addressDTO.getHomeNumber().isEmpty())
                address.setHomeNumber(addressDTO.getHomeNumber());
            if (addressDTO.getStreetName().isEmpty())
                address.setStreetName(addressDTO.getStreetName());

            addressRepository.save(address);
        return "Address update ";
        }
        else return "Address not found ";
    }
}
