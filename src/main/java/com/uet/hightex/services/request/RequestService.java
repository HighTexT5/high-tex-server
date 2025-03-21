package com.uet.hightex.services.request;

import com.uet.hightex.dtos.request.RequestBeADistributorDto;
import com.uet.hightex.dtos.request.RequestSendOpinionOfManagerDto;
import com.uet.hightex.dtos.request.ResponseBeADistributorRequestDetailDto;
import com.uet.hightex.dtos.request.ResponseBeADistributorRequestDto;

import java.util.List;

public interface RequestService {
    void newBeADistributorRequest(String userCode, RequestBeADistributorDto requestBeADistributorDto);

    List<ResponseBeADistributorRequestDto> getBeADistributorRequests(String managerCode);

    ResponseBeADistributorRequestDetailDto getBeADistributorRequestDetail(Long requestId);

    void opinionFromManager(String managerCode, RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto);

    List<ResponseBeADistributorRequestDto> getUserBeADistributorRequest(String userCode);
}
