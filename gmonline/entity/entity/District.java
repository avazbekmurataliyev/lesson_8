    package uz.monopoliya.gmonline.entity.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class District {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false)
        private String districtName ;

        @ManyToOne
        private  Region region ;

    }
