package com.uet.hightex.services.impl.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uet.hightex.dtos.request.*;
import com.uet.hightex.dtos.request.items.SmartphoneRequest;
import com.uet.hightex.entities.common.Item;
import com.uet.hightex.entities.common.Shop;
import com.uet.hightex.entities.common.User;
import com.uet.hightex.entities.common.UserData;
import com.uet.hightex.entities.items.SmartphoneInfo;
import com.uet.hightex.entities.items.SmartphoneInfoRequest;
import com.uet.hightex.entities.manager.ActiveItemRequest;
import com.uet.hightex.entities.manager.BeDistributorRequest;
import com.uet.hightex.enums.common.RequestStatus;
import com.uet.hightex.enums.common.UserLockStatus;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.repositories.common.ItemRepository;
import com.uet.hightex.repositories.common.ShopRepository;
import com.uet.hightex.repositories.common.UserDataRepository;
import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.repositories.items.SmartphoneInfoRepository;
import com.uet.hightex.repositories.items.SmartphoneInfoRequestRepository;
import com.uet.hightex.repositories.manager.ActiveItemRequestRepository;
import com.uet.hightex.repositories.manager.BeDistributorRequestRepository;
import com.uet.hightex.services.common.ShopService;
import com.uet.hightex.services.request.RequestService;
import com.uet.hightex.services.support.EmailService;
import com.uet.hightex.utils.DateUtils;
import com.uet.hightex.utils.MapperUtils;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {
    private final BeDistributorRequestRepository beDistributorRequestRepository;
    private final ActiveItemRequestRepository activeItemRequestRepository;
    private final SmartphoneInfoRequestRepository smartphoneInfoRequestRepository;
    private final UserDataRepository userDataRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final SmartphoneInfoRepository smartphoneInfoRepository;

    private final EmailService emailService;
    private final ShopService shopService;


    @Autowired
    public RequestServiceImpl(BeDistributorRequestRepository beDistributorRequestRepository,
                              UserDataRepository userDataRepository,
                              UserRepository userRepository,
                              EmailService emailService,
                              ShopService shopService,
                              ShopRepository shopRepository,
                              ActiveItemRequestRepository activeItemRequestRepository,
                              SmartphoneInfoRequestRepository smartphoneInfoRequestRepository,
                              ItemRepository itemRepository,
                              SmartphoneInfoRepository smartphoneInfoRepository) {
        this.beDistributorRequestRepository = beDistributorRequestRepository;
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.shopService = shopService;
        this.shopRepository = shopRepository;
        this.activeItemRequestRepository = activeItemRequestRepository;
        this.smartphoneInfoRequestRepository = smartphoneInfoRequestRepository;
        this.itemRepository = itemRepository;
        this.smartphoneInfoRepository = smartphoneInfoRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newBeADistributorRequest(String userCode, RequestBeADistributorDto requestBeADistributorDto) {
        if (beDistributorRequestRepository.existsByUserCodeAndStatus(userCode, RequestStatus.PENDING.getValue())) {
            log.error("Request already exists");
            throw new RuntimeException("Request already exists");
        }
        BeDistributorRequest request = new BeDistributorRequest();
        request.setUserCode(userCode);
        request.setShopName(requestBeADistributorDto.getShopName());
        request.setShopWarehouseAddress(requestBeADistributorDto.getShopWarehouseAddress());
        request.setShopPhone(requestBeADistributorDto.getShopPhone());
        request.setShopEmail(requestBeADistributorDto.getShopEmail());
        request.setShopTaxCode(requestBeADistributorDto.getShopTaxCode());
        request.setDescription(requestBeADistributorDto.getDescription());
        request.setStatus(RequestStatus.PENDING.getValue());

        UserData userData = userDataRepository.findByUserCode(request.getUserCode())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserData manager = userDataRepository.findByFullName(requestBeADistributorDto.getManagerName(), UserType.ADMIN.getValue(), UserLockStatus.UNLOCKED.getValue()).orElse(null);
        if (manager == null) {
            List<UserData> admin = userDataRepository.findRoleAdministratorByRegion(userData.getRegion(), UserType.ADMIN.getValue(), UserLockStatus.UNLOCKED.getValue());
            if (admin.isEmpty()) {
                log.error("No admin found");
                throw new RuntimeException("No admin found");
            }

            request.setManagerCode(admin.get(0).getUserCode());
        } else {
            request.setManagerCode(manager.getUserCode());
        }
        request.setRegion(userData.getRegion());

        beDistributorRequestRepository.save(request);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ResponseBeADistributorRequestDto> getBeADistributorRequests(String managerCode) {
        List<BeDistributorRequest> request = beDistributorRequestRepository.findByManagerCodeAndStatus(managerCode, RequestStatus.PENDING.getValue());
        return request.stream().map(r -> {
            ResponseBeADistributorRequestDto response = new ResponseBeADistributorRequestDto();
            response.setRequestId(r.getId());
            response.setUserCode(r.getUserCode());
            response.setShopName(r.getShopName());
            response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(r.getStatus())).getDescription());
            UserData userData = userDataRepository.findByUserCode(r.getUserCode()).orElseThrow(() -> new RuntimeException("User not found"));
            response.setFullName(userData.getFullName());
            return response;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBeADistributorRequestDetailDto getBeADistributorRequestDetail(Long requestId) {
        BeDistributorRequest request = beDistributorRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        UserData userData = userDataRepository.findByUserCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("User not found"));
        ResponseBeADistributorRequestDetailDto response = new ResponseBeADistributorRequestDetailDto();
        response.setFullName(userData.getFullName());
        response.setUserCode(request.getUserCode());
        response.setShopName(request.getShopName());
        response.setShopWarehouseAddress(request.getShopWarehouseAddress());
        response.setShopPhone(request.getShopPhone());
        response.setShopEmail(request.getShopEmail());
        response.setShopTaxCode(request.getShopTaxCode());
        response.setDescription(request.getDescription());
        response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(request.getStatus())).getDescription());
        try {
            UserData createdBy = userDataRepository.findByUserCode(request.getCreatedBy()).orElseThrow(() -> new RuntimeException("Manager not found"));
            response.setCreatedBy(createdBy.getFullName());
            UserData modifiedBy = userDataRepository.findByUserCode(request.getModifiedBy()).orElseThrow(() -> new RuntimeException("Manager not found"));
            response.setModifiedBy(modifiedBy.getFullName());
        } catch (Exception e) {
            response.setCreatedBy("System");
            response.setModifiedBy("System");
        }
        response.setCreatedDate(DateUtils.formatDate(request.getCreatedDate()));
        response.setModifiedDate(DateUtils.formatDate(request.getModifiedDate()));
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void opinionFromManager(String managerCode, RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto) {
        BeDistributorRequest request = beDistributorRequestRepository.findById(requestSendOpinionOfManagerDto.getRequestId()).orElseThrow(() -> new RuntimeException("Request not found"));
        if (managerCode != null && !managerCode.equals(request.getManagerCode())) {
            log.error("Manager not found");
            throw new RuntimeException("Manager not found");
        }
        request.setStatus(Objects.requireNonNull(RequestStatus.fromCode(requestSendOpinionOfManagerDto.getOpinion())).getValue());
        request.setOpinion(requestSendOpinionOfManagerDto.getDetail());

        beDistributorRequestRepository.save(request);

        UserData manager = userDataRepository.findByUserCode(managerCode).orElseThrow(() -> new RuntimeException("Manager not found"));

        UserData userData = userDataRepository.findByUserCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("User not found"));


        User user = userRepository.findByCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("User authentication not found"));

        if (request.getStatus().equals(RequestStatus.APPROVED.getValue())) {
            user.setType(UserType.DISTRIBUTOR.getValue());
            userRepository.save(user);
            userData.setRole(UserType.DISTRIBUTOR.getValue());
            userDataRepository.save(userData);
            shopService.createShop(request);
        }

        if (request.getStatus().equals(RequestStatus.APPROVED.getValue())) {
            String subject = "HighTEx Request Approved";
            String template = "success-request-distributor-email";
            Context context = new Context();
            context.setVariable("fullName", userData.getFullName());
            context.setVariable("shopName", request.getShopName());
            context.setVariable("managerName", manager.getFullName());
            context.setVariable("opinion", requestSendOpinionOfManagerDto.getOpinion());
            context.setVariable("detail", this.formatString(requestSendOpinionOfManagerDto.getDetail()));
            try {
                emailService.sendEmail(user.getEmail(), subject, template, context);
            } catch (Exception e) {
                log.error("Failed to send OTP to email", e);
                try {
                    throw new MessagingException("Failed to send OTP to email");
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            String subject = "HighTEx Request Rejected";
            String template = "failure-request-distributor-email";
            Context context = new Context();
            context.setVariable("fullName", userData.getFullName());
            context.setVariable("shopName", request.getShopName());
            context.setVariable("managerName", manager.getFullName());
            context.setVariable("opinion", requestSendOpinionOfManagerDto.getOpinion());
            context.setVariable("detail", requestSendOpinionOfManagerDto.getDetail());
            context.setVariable("supportedEmail", this.formatString(manager.getEmail()));
            try {
                emailService.sendEmail(user.getEmail(), subject, template, context);
            } catch (Exception e) {
                log.error("Failed to send OTP to email", e);
                try {
                    throw new MessagingException("Failed to send OTP to email");
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void opinionFromManagerOnItem(String managerCode, RequestSendOpinionOfManagerDto requestSendOpinionOfManagerDto) {
        ActiveItemRequest request = activeItemRequestRepository.findById(requestSendOpinionOfManagerDto.getRequestId()).orElseThrow(() -> new RuntimeException("Request not found"));
        if (managerCode != null && !managerCode.equals(request.getManagerCode())) {
            log.error("Manager not found");
            throw new RuntimeException("Manager not found");
        }
        request.setStatus(Objects.requireNonNull(RequestStatus.fromCode(requestSendOpinionOfManagerDto.getOpinion())).getValue());
        request.setOpinion(requestSendOpinionOfManagerDto.getDetail());

        activeItemRequestRepository.save(request);

        UserData manager = userDataRepository.findByUserCode(managerCode).orElseThrow(() -> new RuntimeException("Manager not found"));
        UserData userData = userDataRepository.findByUserCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userRepository.findByCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("User authentication not found"));
        Shop shop = shopRepository.findByOwnerCode(request.getUserCode()).orElseThrow(() -> new RuntimeException("Shop not found"));


        if (request.getStatus().equals(RequestStatus.APPROVED.getValue())) {
            Item item = new Item();
            MapperUtils.map(request, item);
            item.setId(null);
            item.setItemCode(this.createItemCode(request.getCategory()));
            item.setOriginPrice(request.getPrice());
            item.setCurrentPrice(request.getPrice());
            item.setActive(true);
            item.setDeleted(false);
            item.setRevenue(0.0);
            item.setSalesAmount(0);
            item.setItemInfoId(this.saveInfoData(request.getCategory(), request.getItemDetailId()));

            itemRepository.save(item);

            String subject = "HighTEx Request Approved";
            String template = "success-active-item-email";
            Context context = new Context();
            context.setVariable("fullName", userData.getFullName());
            context.setVariable("shopName", shop.getShopName());
            context.setVariable("productName", item.getItemName());
            context.setVariable("productCode", item.getItemCode());
            context.setVariable("category", item.getCategory());
            context.setVariable("price", item.getOriginPrice());
            context.setVariable("supportedEmail", this.formatString(manager.getEmail()));
            try {
                emailService.sendEmail(user.getEmail(), subject, template, context);
            } catch (Exception e) {
                log.error("Failed to send OTP to email", e);
                try {
                    throw new MessagingException("Failed to send OTP to email");
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            String subject = "HighTEx Request Rejected";
            String template = "failure-active-item-email";
            Context context = new Context();
            context.setVariable("fullName", userData.getFullName());
            context.setVariable("shopName", shop.getShopName());
            context.setVariable("productName", request.getItemName());
            context.setVariable("managerName", manager.getFullName());
            context.setVariable("opinion", requestSendOpinionOfManagerDto.getOpinion());
            context.setVariable("detail", requestSendOpinionOfManagerDto.getDetail());
            context.setVariable("supportedEmail", this.formatString(manager.getEmail()));
            try {
                emailService.sendEmail(user.getEmail(), subject, template, context);
            } catch (Exception e) {
                log.error("Failed to send OTP to email", e);
                try {
                    throw new MessagingException("Failed to send OTP to email");
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private long saveInfoData(String category, Long id) {
        switch (category) {
            case "SMARTPHONE":
            case "smartphone":
            {
                SmartphoneInfoRequest smartphoneInfoRequest = smartphoneInfoRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Info not found"));
                SmartphoneInfo smartphoneInfo = new SmartphoneInfo();
                MapperUtils.map(smartphoneInfoRequest, smartphoneInfo);
                smartphoneInfo.setId(null);
                smartphoneInfoRepository.save(smartphoneInfo);
                return smartphoneInfo.getId();
            }
            default:
                return 0;
        }
    }

    private String createItemCode(String category) {
        String code = category.substring(0, 2).toUpperCase();
        code += String.format("%04d", (int) (Math.random() * 10000));
        code += System.currentTimeMillis();
        return code;
    }

    @Override
    public List<ResponseBeADistributorRequestDto> getUserBeADistributorRequest(String userCode) {
        List<BeDistributorRequest> request = beDistributorRequestRepository.findByUserCode(userCode);
        return request.stream().map(r -> {
            ResponseBeADistributorRequestDto response = new ResponseBeADistributorRequestDto();
            response.setRequestId(r.getId());
            response.setFullName(userDataRepository.findByUserCode(r.getUserCode()).orElseThrow(() -> new RuntimeException("User not found")).getFullName());
            response.setUserCode(r.getUserCode());
            response.setShopName(r.getShopName());
            response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(r.getStatus())).getDescription());
            return response;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newActiveItemRequest(String userCode, RequestActiveAnItemDto requestActiveAnItemDto) {
        UserData userData = userDataRepository.findByUserCode(userCode).orElseThrow(() -> new RuntimeException("User not found"));
        Shop shop = shopRepository.findByOwnerCode(userCode).orElseThrow(() -> new RuntimeException("Shop not found"));

        ActiveItemRequest request = new ActiveItemRequest();
        request.setUserCode(userCode);
        request.setShopCode(shop.getShopCode());

        UserData manager = userDataRepository.findByFullName(requestActiveAnItemDto.getManagerName(), UserType.ADMIN.getValue(), UserLockStatus.UNLOCKED.getValue()).orElse(null);
        if (manager == null) {
            List<UserData> admin = userDataRepository.findRoleAdministratorByRegion(userData.getRegion(), UserType.ADMIN.getValue(), UserLockStatus.UNLOCKED.getValue());
            if (admin.isEmpty()) {
                log.error("No admin found");
                throw new RuntimeException("No admin found");
            }

            request.setManagerCode(admin.get(0).getUserCode());
        } else {
            request.setManagerCode(manager.getUserCode());
        }

        request.setRegion(userData.getRegion());
        request.setItemName(requestActiveAnItemDto.getItemName());
        request.setCategory(requestActiveAnItemDto.getCategory());
        request.setPrice(requestActiveAnItemDto.getPrice());
        request.setQuantity(requestActiveAnItemDto.getQuantity());
        request.setDescription(requestActiveAnItemDto.getDescription());
        request.setStatus(RequestStatus.PENDING.getValue());
        request.setBrand(requestActiveAnItemDto.getBrand());
        request.setProductSource(requestActiveAnItemDto.getProductSource());
        request.setItemDetailId(this.saveInfoData(requestActiveAnItemDto.getCategory(), requestActiveAnItemDto.getDetail()));

        activeItemRequestRepository.save(request);
    }

    @Transactional(rollbackFor = Exception.class)
    private long saveInfoData(String category, Object object) {
        switch (category) {
            case "SMARTPHONE":
            case "smartphone":
            {
                ObjectMapper objectMapper = new ObjectMapper();
                SmartphoneRequest smartphoneRequest = objectMapper.convertValue(object, SmartphoneRequest.class);
                SmartphoneInfoRequest smartphoneInfoRequest = new SmartphoneInfoRequest();
                MapperUtils.map(smartphoneRequest, smartphoneInfoRequest);
                smartphoneInfoRequestRepository.save(smartphoneInfoRequest);
                return smartphoneInfoRequest.getId();
            }
            default:
                return 0;
        }
    }

    @Override
    public List<ResponseActiveAnItemRequestDto> getActiveItemRequests(String managerCode) {
        List<ActiveItemRequest> request = activeItemRequestRepository.findByManagerCodeAndStatus(managerCode, RequestStatus.PENDING.getValue());

        return request.stream().map(r -> {
            ResponseActiveAnItemRequestDto response = new ResponseActiveAnItemRequestDto();
            response.setRequestId(r.getId());
            response.setItemName(r.getItemName());
            response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(r.getStatus())).getDescription());
            UserData userData = userDataRepository.findByUserCode(r.getUserCode()).orElseThrow(() -> new RuntimeException("User not found"));
            response.setFullName(userData.getFullName());
            Shop shop = shopRepository.findByOwnerCode(r.getUserCode()).orElseThrow(() -> new RuntimeException("Shop not found"));
            response.setShopName(shop.getShopName());
            return response;
        }).toList();
    }

    @Override
    public ResponseActiveAnItemRequestDetailDto getActiveItemRequestDetail(Long requestId) {
        ActiveItemRequest request = activeItemRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        UserData manager = userDataRepository.findByUserCode(request.getManagerCode()).orElseThrow(() -> new RuntimeException("Manager not found"));
        ResponseActiveAnItemRequestDetailDto response = new ResponseActiveAnItemRequestDetailDto();
        MapperUtils.map(request, response);
        response.setManagerName(manager.getFullName());
        response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(request.getStatus())).getDescription());
        response.setDetail(this.getDetailData(request.getCategory(), request.getItemDetailId()));
        response.setDescription(request.getDescription());

        return response;
    }

    private Object getDetailData(String category, Long id) {
        switch (category) {
            case "SMARTPHONE":
            case "smartphone":
                SmartphoneInfoRequest smartphoneInfoRequest = smartphoneInfoRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Info not found"));
                SmartphoneRequest smartphoneRequest = new SmartphoneRequest();
                MapperUtils.map(smartphoneInfoRequest, smartphoneRequest);
                return smartphoneRequest;
            default:
                return null;
        }
    }

    @Override
    public List<ResponseActiveAnItemRequestDto> getUserActiveItemRequest(String userCode) {
        List<ActiveItemRequest> request = activeItemRequestRepository.findByUserCodeAndStatusNot(userCode, RequestStatus.DELETED.getValue());
        return request.stream().map(r -> {
            ResponseActiveAnItemRequestDto response = new ResponseActiveAnItemRequestDto();
            response.setRequestId(r.getId());
            response.setFullName(userDataRepository.findByUserCode(r.getUserCode()).orElseThrow(() -> new RuntimeException("User not found")).getFullName());
            response.setItemName(r.getItemName());
            response.setStatus(Objects.requireNonNull(RequestStatus.fromValue(r.getStatus())).getDescription());
            return response;
        }).toList();
    }

    private String formatString(String str) {
        return Objects.requireNonNullElse(str, "");
    }
}
