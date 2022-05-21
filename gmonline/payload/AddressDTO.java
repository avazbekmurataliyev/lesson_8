package uz.monopoliya.gmonline.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String streetName ;
    private String homeNumber ;

    private Integer districtId ;

}
