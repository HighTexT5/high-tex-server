package com.uet.hightex.controllers.request;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.request.RequestBeADistributorDto;
import com.uet.hightex.dtos.request.RequestSendOpinionOfManagerDto;
import com.uet.hightex.dtos.request.ResponseBeADistributorRequestDetailDto;
import com.uet.hightex.dtos.request.ResponseBeADistributorRequestDto;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.request.RequestService;
import com.uet.hightex.services.support.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    private final RequestService requestService;
    private final BaseService baseService;

    @Autowired
    public RequestController(RequestService requestService, BaseService baseService) {
        this.requestService = requestService;
        this.baseService = baseService;
    }

    @PostMapping("/user/create-be-a-distributor")
    public BaseResponse<String> createRequest(@RequestBody RequestBeADistributorDto requestBeADistributorDto) {
        try {
            String userCode = baseService.getUserCode();
            requestService.newBeADistributorRequest(userCode, requestBeADistributorDto);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Create request successfully");
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR.getValue(), e.getMessage(), null);
        }
    }

    @GetMapping("/all/get-list-be-a-distributor")
    public BaseResponse<List<ResponseBeADistributorRequestDto>> getUserListRequest() {
        try {
            String userCode = baseService.getUserCode();
            List<ResponseBeADistributorRequestDto> responseBeADistributorRequestDtos = requestService.getUserBeADistributorRequest(userCode);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", responseBeADistributorRequestDtos);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR.getValue(), e.getMessage(), null);
        }
    }

    @GetMapping("/admin/get-list-be-a-distributor")
    public BaseResponse<List<ResponseBeADistributorRequestDto>> getListRequest() {
        try {
            String managerCode = baseService.getUserCode();
            List<ResponseBeADistributorRequestDto> responseBeADistributorRequestDtos = requestService.getBeADistributorRequests(managerCode);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", responseBeADistributorRequestDtos);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR.getValue(), e.getMessage(), null);
        }
    }

    @GetMapping("/all/get-detail-be-a-distributor")
    public BaseResponse<ResponseBeADistributorRequestDetailDto> getDetailRequest(@RequestParam Long requestId) {
        try {
            ResponseBeADistributorRequestDetailDto response = requestService.getBeADistributorRequestDetail(requestId);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", response);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR.getValue(), e.getMessage(), null);
        }
    }

    @PostMapping("/admin/opinion-from-manager")
    public BaseResponse<String> opinionFromManager(@RequestBody RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto) {
        try {
            String managerCode = baseService.getUserCode();
            requestService.opinionFromManager(managerCode, requestSendOpinionOfManagerDto);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Submit opinion successfully");
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR.getValue(), e.getMessage(), null);
        }
    }
}
