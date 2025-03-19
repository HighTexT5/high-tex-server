package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.common.RequestUpdateUserDataDto;
import com.uet.hightex.dtos.common.ResponseUserDataDto;
import com.uet.hightex.entities.common.UserData;
import com.uet.hightex.enums.common.Gender;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.repositories.common.UserDataRepository;
import com.uet.hightex.services.common.UserDataService;
import com.uet.hightex.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
        userData.setRole(UserType.USER.getValue());
        userData.setRegion(this.getRegion());

        userDataRepository.save(userData);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseUserDataDto getUserData(String userCode) {
        UserData userData = userDataRepository.findByUserCode(userCode).orElseThrow(() -> new RuntimeException("User data not found"));
        if (userData == null) {
            log.error("User data not found");
            return null;
        }

        ResponseUserDataDto responseUserDataDto = new ResponseUserDataDto();
        responseUserDataDto.setFullName(userData.getFullName());
        responseUserDataDto.setGender(userData.getGender());
        responseUserDataDto.setBirthday(DateUtils.formatDate(userData.getBirthday()));
        responseUserDataDto.setPhoneNumber(userData.getPhoneNumber());
        responseUserDataDto.setEmail(userData.getEmail());
        responseUserDataDto.setAddress(userData.getAddress());

        return responseUserDataDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserData(String userCode, RequestUpdateUserDataDto requestUpdateUserDataDto) {
        UserData userData = userDataRepository.findByUserCode(userCode).orElseThrow(() -> new RuntimeException("User data not found"));

        userData.setFullName(requestUpdateUserDataDto.getFullName());
        userData.setGender(requestUpdateUserDataDto.getGender());
        userData.setBirthday(DateUtils.parseDate(requestUpdateUserDataDto.getBirthday()));
        userData.setPhoneNumber(requestUpdateUserDataDto.getPhoneNumber());
        userData.setEmail(requestUpdateUserDataDto.getEmail());
        userData.setAddress(requestUpdateUserDataDto.getAddress());

        try {
            userDataRepository.save(userData);
        } catch (Exception e) {
            log.error("Update user data failed", e);
            throw new RuntimeException("Update user data failed");
        }
    }


    private String getRegion() {
        return "ALL";
    }
}
