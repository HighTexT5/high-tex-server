package com.uet.hightex.repositories.manager;

import com.uet.hightex.entities.manager.BeDistributorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeDistributorRequestRepository extends JpaRepository<BeDistributorRequest, Long> {

}
