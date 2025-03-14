package org.example.s29888tpo2;

import org.example.s29888tpo2.formatter.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

}
