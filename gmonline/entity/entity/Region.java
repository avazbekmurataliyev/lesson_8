    package uz.monopoliya.gmonline.entity.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Region {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false ,unique = true)
        private String regionName ;


    }
