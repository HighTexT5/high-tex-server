package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.order.RequestCreateAddressDto;
import com.uet.hightex.dtos.order.ResponseUserReceiveAddress;
import com.uet.hightex.entities.common.UserReceiveAddress;
import com.uet.hightex.repositories.common.UserReceiveAddressRepository;
import com.uet.hightex.services.common.UserReceiveAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserReceiveAddressServiceImpl implements UserReceiveAddressService {
    private final UserReceiveAddressRepository userReceiveAddressRepository;

    @Autowired
    public UserReceiveAddressServiceImpl(UserReceiveAddressRepository userReceiveAddressRepository) {
        this.userReceiveAddressRepository = userReceiveAddressRepository;
    }

    @Override
    public List<ResponseUserReceiveAddress> getAllUserReceiveAddress(String userCode) {
        return userReceiveAddressRepository.findAllByUserCodeAndIsDeletedFalse(userCode)
                .stream()
                .map(ResponseUserReceiveAddress::fromEntity)
                .toList();
    }

    @Override
    public void createUserReceiveAddress(String userCode, RequestCreateAddressDto requestCreateAddressDto) {
        // Create new user receive address
        UserReceiveAddress userReceiveAddress = UserReceiveAddress.builder()
                .userCode(userCode)
                .addressCode("ADDRESS_" + System.currentTimeMillis())
                .province(requestCreateAddressDto.getProvince())
                .district(requestCreateAddressDto.getDistrict())
                .commune(requestCreateAddressDto.getCommune())
                .detail(requestCreateAddressDto.getDetail())
                .phone(requestCreateAddressDto.getPhoneNumber())
                .build();

        List<UserReceiveAddress> userReceiveAddresses = userReceiveAddressRepository.findAllByUserCodeAndIsDeletedFalse(userCode);
        if (userReceiveAddresses.isEmpty()) {
            userReceiveAddress.setDefault(true);
        }

        userReceiveAddressRepository.save(userReceiveAddress);
    }
}
