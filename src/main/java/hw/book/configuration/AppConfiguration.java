package hw.book.configuration;

import hw.book.repository.BookRepository;
import hw.book.repository.BookRepositoryJDBC;
import hw.book.repository.IBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    private ConfigurableApplicationContext context;

    @Bean
    public IBookRepository getRepository() {
        if (repositoryType.equalsIgnoreCase("list")) {
            return context.getBean(BookRepository.class);
        } else {
            return context.getBean(BookRepositoryJDBC.class);
        }
    }

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
