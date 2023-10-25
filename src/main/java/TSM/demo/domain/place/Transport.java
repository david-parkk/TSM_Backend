package TSM.demo.domain.place;


import jakarta.persistence.*;

@Entity
public class Transport extends Place{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
