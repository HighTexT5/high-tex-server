package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.UserReceiveAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReceiveAddressRepository extends JpaRepository<UserReceiveAddress, Long> {
    List<UserReceiveAddress> findAllByUserCodeAndIsDeletedFalse(String userCode);

    Optional<UserReceiveAddress> findByAddressCode(String addressCode);
}
