package uz.monopoliya.gmonline.entity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false)
    private String streetName ;

    @Column(nullable = false,unique = true)
    private String homeNumber ;

    @ManyToOne
    private District district ;

}
