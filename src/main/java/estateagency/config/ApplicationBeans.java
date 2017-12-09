package estateagency.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {
    @Bean
    /**
     * настрока конвертера сущности в dto
     */
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
