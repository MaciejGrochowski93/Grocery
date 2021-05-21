package maciej.grochowski.grocerystore.user;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.registration.token.ConfirmationToken;
import maciej.grochowski.grocerystore.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));
        return user.map(MyUserDetails::new).get();
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findUserByEmail(user.getFirstName()).isPresent();
        if (userExists) {
            throw new IllegalStateException("This user is already registered.");
        }

        String encodedPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), user);

        confirmationTokenService.saveToken(confirmationToken);

        return token;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public void buyProduct(String email, Product product) {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.ifPresent(e -> e.setMoney(e.getMoney() - product.getPrice()));
    }
}
