package ipastushenko.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ipastushenko.training.service.TransferService;
import ipastushenko.training.service.TransferServiceImpl;
import ipastushenko.training.repository.AccountRepository;
import ipastushenko.training.repository.InMemoryAccountRepository;

/**
 * AppConfig
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */

@Configuration
public class AppConfig {
    public @Bean TransferService transferService() {
        return new TransferServiceImpl(accountRepository());
    }

    public @Bean AccountRepository accountRepository() {
        return new InMemoryAccountRepository();
    }
}
