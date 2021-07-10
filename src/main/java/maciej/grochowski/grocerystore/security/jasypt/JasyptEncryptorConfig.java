package maciej.grochowski.grocerystore.security.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptEncryptorConfig {

    public final String JASYPT_ID = "MV4OZrDtyvU8+3bpa/gxuNWRpezo73BMAoWSmlPzWxrJhdTAbB1tW1mOhYNNg0/lEYwLV5uxrt2hO7tBLza8BQ==";
    public final String JASYPT_PASSWORD = "TvuxdiiUKzVE7XpzSTsqAjpm/PCOQuXi";

    @Bean(name = "jasyptBean")
    public StringEncryptor passwordEncryptor() {
        var encryptor = new PooledPBEStringEncryptor();
        var config = new SimpleStringPBEConfig();
        config.setPassword("password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
