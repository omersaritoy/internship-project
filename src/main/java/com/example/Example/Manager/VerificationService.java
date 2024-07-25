package com.example.Example.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private EmailSenderManager emailSenderManager;

    private static final int Expiration = 5;

    public void sendVerification(String email) {

    }
    public String generateVerificationCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

//    public void sendVerificationCode(String email) {
//        String code = generateVerificationCode(); // Doğrulama kodu oluşturur
//        redisTemplate.opsForValue().set(email, code, Expiration, TimeUnit.MINUTES); // Kodu Redis'e kaydeder
//        emailSenderManager.sendMail(email, "Doğrulama Kodu", "Doğrulama kodunuz: " + code); // Email gönderir
//    }
//    // Belirtilen email ve kodu doğrular
//    public boolean verifyCode(String email, String code) {
//        String storedCode = (String) redisTemplate.opsForValue().get(email); // Redis'teki kodu alır
//        return code.equals(storedCode); // Kodları karşılaştırır
//    }
}
