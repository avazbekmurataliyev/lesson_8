package uz.monopoliya.gmonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Car;
import uz.monopoliya.gmonline.entity.Users;
import uz.monopoliya.gmonline.entity.entity.Address;
import uz.monopoliya.gmonline.payload.UserDTO;
import uz.monopoliya.gmonline.repository.CarRepository;
import uz.monopoliya.gmonline.repository.UserRepository;
import uz.monopoliya.gmonline.repository.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AddressRepository addressRepository ;
   @Autowired
    private UserRepository userRepository ;
    @Autowired
    private CarRepository carRepository ;

    @GetMapping
    public List<Users> getAll(){
        return userRepository.findAll() ;

    }

    @GetMapping("/{id}")
    public List<Car> get(@PathVariable Integer id){
        List<Car> allByCarList = carRepository.findAllByCarList(id);
        if (!allByCarList.isEmpty()) {

            return allByCarList ;
        }
        return new ArrayList<>();
    }

    @PostMapping
    public String save(@RequestBody UserDTO userDTO){
        try{
            Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
            if (!optionalAddress.isPresent()){
                return "Address not found ";
            }
            List<Car> carList = new ArrayList<>();
            for (Integer i : userDTO.getCarsId()) {
                Optional<Car> optionalCar = carRepository.findById(i);
                if (!optionalCar.isPresent())
                    return " Car not found ";
                carList.add(optionalCar.get());
            }
            Users user = new Users();
            user.setAddress(optionalAddress.get());
            user.setName(userDTO.getName());
            user.setCarList(carList);
            userRepository.save(user);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
        }

    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id , @RequestBody UserDTO userDTO){
        try{
            Optional<Users> optionalUsers = userRepository.findById(id);
            if (!optionalUsers.isPresent())
                return "User Not found ";
            Users user = optionalUsers.get();

            Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
            if (!optionalAddress.isPresent()){
                    return "Address not found ";
            }
            List<Car> carList = new ArrayList<>();
            for (Integer i :userDTO.getCarsId()) {
                Optional<Car> optionalCar = carRepository.findById(i);
                if (!optionalCar.isPresent())
                    return " Car not found ";
                carList.add(optionalCar.get());
            }
            user.setAddress(optionalAddress.get());
            user.setName(user.getName());
            user.setCarList(carList);
            userRepository.save(user);
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
