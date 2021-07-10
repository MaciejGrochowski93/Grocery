package maciej.grochowski.grocerystore.security.jasypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JasyptService {

    @Value("${spring.mail.username}")
    private String mail;

    public String getMail() {
        return mail;
    }

    @Value("${spring.mail.password}")
    private String password;

    public String getPasswordl() {
        return password;
    }

    public String getPasswordUsingEnvironment(Environment environment) {
        return environment.getProperty("encrypted.property");
    }
}