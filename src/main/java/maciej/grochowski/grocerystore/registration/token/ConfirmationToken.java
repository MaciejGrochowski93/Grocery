package maciej.grochowski.grocerystore.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maciej.grochowski.grocerystore.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String token;

    @Column
    private LocalDateTime creationTime;

    private LocalDateTime expirationTime;

    private LocalDateTime confirmationTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime creationTime, User user){
        this.token = token;
        this.creationTime = creationTime;
        this.expirationTime = this.creationTime.plusMinutes(20);
        this.user = user;
    }

}
