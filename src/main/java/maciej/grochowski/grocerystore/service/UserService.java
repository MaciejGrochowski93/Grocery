package maciej.grochowski.grocerystore.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.ConfirmationToken;
import maciej.grochowski.grocerystore.security.MyUserDetails;
import maciej.grochowski.grocerystore.model.User;
import maciej.grochowski.grocerystore.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));
        return new MyUserDetails(user);
    }

    public User signUpUser(User user) {
        boolean userExists = userRepository.findUserByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("This user is already registered.");
        }

        String encodedPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);
        return user;
    }

    public String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        var confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), user);

        confirmationTokenService.saveToken(confirmationToken);

        return token;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getPrincipal() {
        String email = getPrincipalEmail();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String getPrincipalEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof MyUserDetails) {
            email = ((MyUserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
