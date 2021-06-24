package maciej.grochowski.grocerystore.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String firstName;
}