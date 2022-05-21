package uz.monopoliya.gmonline.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AutoShopDTO {


    private String name ;

    private Integer gmId ;

    private Integer addressId ;

    private List<Integer> carId ;

}
