package org.example.s29888tpo2.formatter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("lowercase")
public class LowercaseWordFormatter implements WordFormatter {
    @Override
    public String format(String word) {
        return word.toLowerCase();
    }
}
