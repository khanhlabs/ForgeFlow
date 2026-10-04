package com.example.backend.common.utils;

import com.example.backend.users.entities.User;
import com.example.backend.users.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeedUtils {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeedUtils(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void insert25Users(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password must not be blank");
        }

        String[][] userData = {
                {"Pham Gia Khanh", "khanhdev206@gmail.com"},
                {"Nguyen Minh Anh", "minh.anh01@example.com"},
                {"Tran Quoc Bao", "quoc.bao02@example.com"},
                {"Le Hoang Nam", "hoang.nam03@example.com"},
                {"Pham Thanh Tung", "thanh.tung04@example.com"},
                {"Vo Gia Huy", "gia.huy05@example.com"},
                {"Dang Minh Khang", "minh.khang06@example.com"},
                {"Bui Duc Anh", "duc.anh07@example.com"},
                {"Hoang Tuan Kiet", "tuan.kiet08@example.com"},
                {"Doan Thanh Phong", "thanh.phong09@example.com"},
                {"Phan Minh Duc", "minh.duc10@example.com"},
                {"Nguyen Hai Dang", "hai.dang11@example.com"},
                {"Tran Minh Quan", "minh.quan12@example.com"},
                {"Le Thanh Dat", "thanh.dat13@example.com"},
                {"Pham Quang Hieu", "quang.hieu14@example.com"},
                {"Vo Thanh Long", "thanh.long15@example.com"},
                {"Dang Hoai Nam", "hoai.nam16@example.com"},
                {"Bui Minh Tri", "minh.tri17@example.com"},
                {"Hoang Duc Thinh", "duc.thinh18@example.com"},
                {"Do Thi Ngoc Anh", "ngoc.anh19@example.com"},
                {"Nguyen Thu Ha", "thu.ha20@example.com"},
                {"Tran Bao Ngoc", "bao.ngoc21@example.com"},
                {"Le Phuong Linh", "phuong.linh22@example.com"},
                {"Pham Khanh Vy", "khanh.vy23@example.com"},
                {"Vo Ngoc Mai", "ngoc.mai24@example.com"}
        };

        List<User> users = new ArrayList<>(userData.length);
        for (String[] data : userData) {
            User user = new User();
            user.setFullName(data[0]);
            user.setEmail(data[1]);
            user.setPasswordHash(passwordEncoder.encode(rawPassword));
            users.add(user);
        }

        userRepository.saveAll(users);
    }
}
