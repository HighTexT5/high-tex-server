package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserCode(String userCode);

    @Query("SELECT ud FROM UserData ud LEFT JOIN User u ON u.code = ud.userCode " +
            "WHERE ud.fullName = :fullName AND ud.role = :role AND u.lockStatus = :lockStatus")
    Optional<UserData> findByFullName(@Param("fullName") String fullName, @Param("role") Integer role, @Param("lockStatus") Integer lockStatus);

    @Query("SELECT ud FROM UserData ud LEFT JOIN User u ON u.code = ud.userCode " +
            "WHERE ud.region = :region AND ud.role = :role AND u.lockStatus = :lockStatus")
    List<UserData> findRoleAdministratorByRegion(@Param("region") String region, @Param("role") Integer role, @Param("lockStatus") Integer lockStatus);
}
