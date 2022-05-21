package uz.monopoliya.gmonline.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DistrictDTO {

    private String districtName ;

   private Integer regionId ;
}
