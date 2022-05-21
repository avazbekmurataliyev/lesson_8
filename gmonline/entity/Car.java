    package uz.monopoliya.gmonline.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import uz.monopoliya.gmonline.entity.entity.Region;

    import javax.persistence.*;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false)
        private String model ;
        @Column (nullable = false)
        private int madeYear ;
        @Column (nullable = false)
        private String type ;
        @Column(nullable = false)
        private String stateNumber ;
        @ManyToOne
        private Region region ;
    }
