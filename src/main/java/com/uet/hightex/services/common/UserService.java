package com.uet.hightex.services.common;

import org.springframework.stereotype.Service;

public interface UserService {
    boolean signup(String username, String password);
}
