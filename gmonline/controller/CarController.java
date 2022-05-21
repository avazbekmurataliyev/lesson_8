package uz.monopoliya.gmonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.monopoliya.gmonline.entity.Car;
import uz.monopoliya.gmonline.repository.CarRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarRepository carRepository ;

    @GetMapping
    public List<Car> getAll(){
        return carRepository.findAll() ;

    }

    @GetMapping("/{id}")
    public Car get(@PathVariable Integer id){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            return optionalCar.get();
        }
        return new Car();
    }

    @PostMapping
    public String save(@RequestBody Car car){
        try{
            if (car != null )
            carRepository.save(car);
            return "Information saved ";
        }catch (Exception e){
            return "Error save this information ";
        }

    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody Car car){
        try {
            Optional<Car> optionalCar = carRepository.findById(id);
            if (!optionalCar.isPresent())return "Not found ";
            Car car1 = optionalCar.get();
            if (!car.getModel().isEmpty())car1.setModel(car.getModel());
            if ( car.getPrice() > 0 ) car1.setPrice(car.getPrice());
            if (car.getYear() > 0)car1.setYear(car.getYear());
           carRepository.save(car1);
            return "Successful update " ;

        }catch (Exception e){
            return " Error update data " ;

        }

    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id){

        try {

            Optional<Car> optionalCar = carRepository.findById(id);
            if (optionalCar.isPresent()){
                carRepository.deleteById(id);
                return "Deleted info " ;
            }

            return " This Object not found " ;
        }catch (Exception e){
            return "Error deleted information ";
        }

    }
}
