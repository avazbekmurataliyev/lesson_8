package uz.monopoliya.gmonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.monopoliya.gmonline.entity.entity.Address;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name ;

    @ManyToMany
    private List<Car> carList ;

    @OneToOne
    private Address address ;

}
