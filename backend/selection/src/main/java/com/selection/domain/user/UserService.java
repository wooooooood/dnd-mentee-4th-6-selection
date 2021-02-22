package com.selection.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException(
            String.format("해당 이메일(%s)는 존재하지 하는 유저 이메일입니다.", userId)
        ));
    }
}
