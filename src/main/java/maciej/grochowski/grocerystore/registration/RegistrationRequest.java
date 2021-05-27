package maciej.grochowski.grocerystore.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    private String email;
    private String password;
    private String firstName;
}