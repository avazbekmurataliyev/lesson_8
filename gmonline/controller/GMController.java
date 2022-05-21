package uz.monopoliya.gmonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Address;
import uz.monopoliya.gmonline.entity.AutoShop;
import uz.monopoliya.gmonline.entity.GM;
import uz.monopoliya.gmonline.payload.GmDTO;
import uz.monopoliya.gmonline.repository.AddressRepository;
import uz.monopoliya.gmonline.repository.AutoShopRepository;
import uz.monopoliya.gmonline.repository.GMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gm")
public class GMController {

    @Autowired
    private GMRepository gmRepository ;

    @Autowired
    private AutoShopRepository autoShopRepository ;

    @Autowired
    private AddressRepository addressRepository ;

    @GetMapping
    public List<GM> getAll(){
        return gmRepository.findAll() ;
        
    }

    @GetMapping("/{id}")
    public List<AutoShop> getShops(@PathVariable Integer id){
        if (gmRepository.findById(id).isPresent()){
            return autoShopRepository.findAllByGm_Id(id);
        }
    return new ArrayList<>();
    }

    @PostMapping
    public String save(@RequestBody GmDTO gmDTO){
        try{
            Optional<Address> optionalAddress = addressRepository.findById(gmDTO.getAddressId());
            if (!optionalAddress.isPresent()){
                return "Address not found ";
            }
            GM gm = new GM();
            gm.setAddress(optionalAddress.get());
            gm.setCorpName(gmDTO.getCorpName());
            gm.setDirectorName(gmDTO.getDirectorName());
            gmRepository.save(gm);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
        }

    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody GmDTO gmDTO){
        try {
            Optional<GM> optionalGM = gmRepository.findById(id);
            Address address = new Address() ;
            if (!optionalGM.isPresent()){
                return "Information not found ";
            }
            GM gm = optionalGM.get();
           if (gmDTO.getAddressId() != null) {
               Optional<Address> optionalAddress = addressRepository.findById(gmDTO.getAddressId());
               if (!optionalAddress.isPresent())
                   return "Address not found ";
               address = optionalAddress.get();
           }
            if (gmDTO.getDirectorName().isEmpty())gm.setDirectorName(gmDTO.getDirectorName());
            if (gmDTO.getCorpName().isEmpty())gm.setCorpName(gmDTO.getCorpName());
            if (gmDTO.getAddressId()!=null)gm.setAddress(address);
            gmRepository.save(gm);
            return "Successful update " ;

        }catch (Exception e){
            return " Error update data " ;

        }

    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id){

        try {

            Optional<GM> optionalGM = gmRepository.findById(id);
            if (optionalGM.isPresent()){
                gmRepository.deleteById(id);
                return "Deleted info " ;
            }

            return " This Object not found " ;
        }catch (Exception e){
            return "Error deleted information ";
        }

    }
}
