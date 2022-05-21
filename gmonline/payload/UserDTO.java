package uz.monopoliya.gmonline.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name ;

    private List<Integer> carsId ;


    private Integer addressId ;
}
