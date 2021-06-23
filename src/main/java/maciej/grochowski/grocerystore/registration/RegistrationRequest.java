package maciej.grochowski.grocerystore.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    @Size(min = 6, max = 30)
    private String email;
    @Size(min = 6, max = 30)
    private String password;
    @Size(min = 1, max = 30)
    private String firstName;
}