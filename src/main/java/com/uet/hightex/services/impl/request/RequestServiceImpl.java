package com.uet.hightex.services.impl.request;

import com.uet.hightex.repositories.manager.BeDistributorRequestRepository;
import com.uet.hightex.services.request.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {
    private final BeDistributorRequestRepository beDistributorRequestRepository;

    @Autowired
    public RequestServiceImpl(BeDistributorRequestRepository beDistributorRequestRepository) {
        this.beDistributorRequestRepository = beDistributorRequestRepository;
    }

    @Override
    public void newRequest() {
        log.info("New request");




    }
}
