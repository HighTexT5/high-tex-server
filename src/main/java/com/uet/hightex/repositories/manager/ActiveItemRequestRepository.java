package com.uet.hightex.repositories.manager;

import com.uet.hightex.entities.manager.ActiveItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveItemRequestRepository extends JpaRepository<ActiveItemRequest, Long> {
    List<ActiveItemRequest> findByManagerCode(String managerCode);

    List<ActiveItemRequest> findByManagerCodeAndStatus(String managerCode, Integer status);
}
