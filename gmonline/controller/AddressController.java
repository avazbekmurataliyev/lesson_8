package uz.monopoliya.gmonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Address;
import uz.monopoliya.gmonline.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adress")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository ;

    @GetMapping
    public List<Address> getAll(){
        return addressRepository.findAll() ;
        
    }

    @GetMapping("/{id}")
    public Address get(@PathVariable Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            return optionalAddress.get();
        }
    return new Address();
    }

    @PostMapping
    public String save(@RequestBody Address address){
        try{
         if (address!=null)
          addressRepository.save(address);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
        }

    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody Address address){
        try {
            Optional<Address> optionalAddress = addressRepository.findById(id);
            if (!optionalAddress.isPresent())return "Not found ";
            Address address1 = optionalAddress.get();
            if (!address.getRegionName().isEmpty())address1.setRegionName(address.getRegionName());
            if (!address.getHomeNumber().isEmpty()) address1.setHomeNumber(address.getHomeNumber());
            if (!address.getDistrectName().isEmpty())address1.setDistrectName(address.getDistrectName());
            if (!address.getStreetName().isEmpty())address1.setStreetName(address.getStreetName());
            addressRepository.save(address1);
            return "Successful update " ;

        }catch (Exception e){
            return " Error update data " ;

        }

    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id){

        try {

            Optional<Address> optionalAddress = addressRepository.findById(id);
            if (optionalAddress.isPresent()){
                addressRepository.deleteById(id);
                return "Deleted info " ;
            }

            return " This Object not found " ;
        }catch (Exception e){
            return "Error deleted information ";
        }

    }
}
