package com.uet.hightex.repositories.items;

import com.uet.hightex.entities.items.SmartphoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneInfoRepository extends JpaRepository<SmartphoneInfo, Long> {
}
