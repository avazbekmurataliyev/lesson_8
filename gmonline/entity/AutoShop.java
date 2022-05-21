    package uz.monopoliya.gmonline.entity;


    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import java.util.List;
@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class AutoShop {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;

        @Column(nullable = false)
        private String name ;

        @ManyToOne
        private GM gm ;

        @ManyToOne
        private Address address ;

        @ManyToMany
        private List<Car> carList ;


    }
