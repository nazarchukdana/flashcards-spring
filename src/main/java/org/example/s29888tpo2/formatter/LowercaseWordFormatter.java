package org.example.s29888tpo2.formatter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("lowercase")
public class LowercaseWordFormatter implements WordFormatter {
    @Override
    public String format(String word) {
        return word.toLowerCase();
    }
}
