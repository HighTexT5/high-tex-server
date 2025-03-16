package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserCode(String userCode);
}
