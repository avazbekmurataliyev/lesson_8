    package uz.monopoliya.gmonline.entity;

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
        private String regionName ;
        @Column(nullable = false)
        private String distrectName ;
        @Column(nullable = false)
        private String streetName ;
        @Column(nullable = false)
        private String homeNumber ;


    }
