package edu.pja.s30051tpo02;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("lowercase")

public class LowercaseFormatter implements Formatter {

    @Override
    public String format(String data) {
        return data.toLowerCase();
    }
}
