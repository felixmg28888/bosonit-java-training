package com.bosonit.formacion.block0504profiles;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${bd.url}")
    private String bdurl;
}
