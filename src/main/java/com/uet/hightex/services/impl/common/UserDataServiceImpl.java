package com.uet.hightex.services.impl.common;

import com.uet.hightex.entities.common.UserData;
import com.uet.hightex.repositories.common.UserDataRepository;
import com.uet.hightex.services.common.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserData(String userCode, String fullName) {
        UserData userData = new UserData();
        userData.setUserCode(userCode);
        userData.setFullName(fullName);

        userDataRepository.save(userData);
    }
}
