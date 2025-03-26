package com.uet.hightex.repositories.items;

import com.uet.hightex.entities.items.SmartphoneInfoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneInfoRequestRepository extends JpaRepository<SmartphoneInfoRequest, Long> {

}
