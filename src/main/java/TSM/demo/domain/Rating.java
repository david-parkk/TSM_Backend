package TSM.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "rating")
@Getter
@Setter
public class Rating {

    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int ratingId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User senderId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private User receiverId;

    @NotNull
    private int star;

    public Rating(User senderId, User receiverId, int star) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.star = star;
    }

    public Rating() {

    }
}