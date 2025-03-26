package com.uet.hightex.services.request;

import com.uet.hightex.dtos.request.*;

import java.util.List;

public interface RequestService {
    void newBeADistributorRequest(String userCode, RequestBeADistributorDto requestBeADistributorDto);

    List<ResponseBeADistributorRequestDto> getBeADistributorRequests(String managerCode);

    ResponseBeADistributorRequestDetailDto getBeADistributorRequestDetail(Long requestId);

    void opinionFromManager(String managerCode, RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto);

    void opinionFromManagerOnItem(String managerCode, RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto);

    List<ResponseBeADistributorRequestDto> getUserBeADistributorRequest(String userCode);

    void newActiveItemRequest(String userCode, RequestActiveAnItemDto requestActiveAnItemDto);

    List<ResponseActiveAnItemRequestDto> getActiveItemRequests(String managerCode);
}
