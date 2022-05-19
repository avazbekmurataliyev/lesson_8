package uz.avazbek.map.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.avazbek.map.entity.District;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String streetName ;
    private String homeNumber ;

    private Integer districtId ;

}
