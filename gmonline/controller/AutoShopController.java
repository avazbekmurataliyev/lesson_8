package uz.monopoliya.gmonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Address;
import uz.monopoliya.gmonline.entity.AutoShop;
import uz.monopoliya.gmonline.entity.Car;
import uz.monopoliya.gmonline.entity.GM;
import uz.monopoliya.gmonline.payload.AutoShopDTO;
import uz.monopoliya.gmonline.repository.AddressRepository;
import uz.monopoliya.gmonline.repository.AutoShopRepository;
import uz.monopoliya.gmonline.repository.CarRepository;
import uz.monopoliya.gmonline.repository.GMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autoShop")
public class AutoShopController {

    @Autowired
    private AddressRepository addressRepository ;
    @Autowired
    private AutoShopRepository autoShopRepository ;
    @Autowired
    private GMRepository gmRepository ;
    @Autowired
    private CarRepository carRepository ;

    @GetMapping
    public List<AutoShop> getAll(){
        return autoShopRepository.findAll() ;

    }

    @GetMapping("/{id}")
    public List<Car> get(@PathVariable Integer id){
        List<Car> carList = new ArrayList<>();
        List<Car> allByCarList = carRepository.findAllByCarList(id);
        if (!allByCarList.isEmpty()) {
            for (Car car : allByCarList) {
                carList.add(car);
            }
            return carList ;
        }
        return new ArrayList<>();
    }

    @PostMapping
    public String save(@RequestBody AutoShopDTO autoShopDTO){
        try{
            Optional<Address> optionalAddress = addressRepository.findById(autoShopDTO.getAddressId());
            if (!optionalAddress.isPresent()){
                return "Address not found ";
            }
            Optional<GM> optionalGM = gmRepository.findById(autoShopDTO.getGmId());
            if (!optionalGM.isPresent())
                        return "GM information not found ";
            List<Car> carList = new ArrayList<>();
            for (Integer i :autoShopDTO.getCarId()) {
                Optional<Car> optionalCar = carRepository.findById(i);
                if (!optionalCar.isPresent())
                    return " Car not found ";
                carList.add(optionalCar.get());
            }
            AutoShop autoShop = new AutoShop();
            autoShop.setAddress(optionalAddress.get());
            autoShop.setGm(optionalGM.get());
            autoShop.setName(autoShopDTO.getName());
            autoShop.setCarList(carList);
            autoShopRepository.save(autoShop);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
        }

    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id , @RequestBody AutoShopDTO autoShopDTO){
        try{
            Optional<AutoShop> optionalAutoShop = autoShopRepository.findById(id);
            if (!optionalAutoShop.isPresent())
                return "AutoShop Not found ";
            AutoShop autoShop = optionalAutoShop.get();

            Optional<Address> optionalAddress = addressRepository.findById(autoShopDTO.getAddressId());
            if (!optionalAddress.isPresent()){
                    return "Address not found ";
            }
            Optional<GM> optionalGM = gmRepository.findById(autoShopDTO.getGmId());
            if (!optionalGM.isPresent())
                    return "GM information not found ";
            List<Car> carList = new ArrayList<>();
            for (Integer i :autoShopDTO.getCarId()) {
                Optional<Car> optionalCar = carRepository.findById(i);
                if (!optionalCar.isPresent())
                    return " Car not found ";
                carList.add(optionalCar.get());
            }
            autoShop.setAddress(optionalAddress.get());
            autoShop.setGm(optionalGM.get());
            autoShop.setName(autoShopDTO.getName());
            autoShop.setCarList(carList);
            autoShopRepository.save(autoShop);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
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
