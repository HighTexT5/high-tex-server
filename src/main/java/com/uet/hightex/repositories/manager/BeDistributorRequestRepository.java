package com.uet.hightex.repositories.manager;

import com.uet.hightex.entities.manager.BeDistributorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeDistributorRequestRepository extends JpaRepository<BeDistributorRequest, Long> {
    boolean existsByUserCodeAndStatus(String userCode, Integer status);

    List<BeDistributorRequest> findByManagerCodeAndStatus(String managerCode, Integer status);

    List<BeDistributorRequest> findByUserCode(String userCode);
}
