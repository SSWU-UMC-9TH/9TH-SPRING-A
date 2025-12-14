package spring.umc.global.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String raw = "string"; // 원래 비밀번호
        String encoded = encoder.encode(raw);

        System.out.println(encoded);
    }
}
