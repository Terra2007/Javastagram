package com.example.javastagram.domain.user.facade;

import com.example.javastagram.domain.user.domain.User;
import com.example.javastagram.domain.user.domain.repository.UserRepository;
import com.example.javastagram.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return findUserByAccountId(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Boolean existsUserByAccountId(String accountId) {
        return userRepository.existsUserByAccountId(accountId);
    }

    public User findUserByAccountId(String accountId) {
        return userRepository.findUserByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
