package org.pass;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassEnde {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static void main(String[] args) {
        String encode = ENCODER.encode("123456");
        System.out.printf("encode:" + encode);
    }
}
