package com.uet.hightex.services.impl.common;

import com.uet.hightex.entities.common.User;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.services.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signup(String username, String password) {
        if (isUsernameExist(username)) {
            log.error("Username {} is already exist", username);
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCode(createUserCode());
        user.setType(UserType.USER.getValue());
        user.setLockStatus(UserLockStatus.UNLOCKED.getValue());

        userRepository.save(user);

        return true;
    }

    private boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private String createUserCode() {
        return "U" + System.currentTimeMillis();
    }
}
