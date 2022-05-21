    package uz.monopoliya.gmonline.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class GM {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column (nullable = false)
        private String corpName ;
        @Column (nullable = false)
        private String directorName ;
        @OneToOne(optional = false)
        private Address address ;


    }
