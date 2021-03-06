package maciej.grochowski.grocerystore.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.ConfirmationToken;
import maciej.grochowski.grocerystore.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    public void saveToken(ConfirmationToken token) {
        repository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    public int confirmToken(String token) {
        return repository.updateConfirmationTime(token, LocalDateTime.now());
    }
}
