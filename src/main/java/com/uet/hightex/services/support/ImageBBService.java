package com.uet.hightex.services.support;

import com.uet.hightex.entities.common.Item;
import com.uet.hightex.repositories.common.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
@Slf4j
public class ImageBBService {
    @Value("${imgbb.api.url}")
    private String imgbbApiUrl;

    @Value("${imgbb.api.key}")
    private String imgbbApiKey;

    private final RestTemplate restTemplate;
    private final ItemRepository itemRepository;

    @Autowired
    public ImageBBService(RestTemplate restTemplate, ItemRepository itemRepository) {
        this.restTemplate = restTemplate;
        this.itemRepository = itemRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public String uploadAndSaveThumbnailImage(MultipartFile file, Long itemId) {
        try {
            String url = uploadToImgBB(file);
            Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
            item.setThumbnailUrl(url);
            itemRepository.save(item);
            return url;
        } catch (Exception e) {
            log.error("Failed to upload image to ImgBB", e);
            return null;
        }
    }

    public String uploadAndGetLink(MultipartFile file) {
        try {
            return uploadToImgBB(file);
        } catch (Exception e) {
            log.error("Failed to upload image to ImgBB", e);
            return null;
        }
    }

    private String uploadToImgBB(MultipartFile file) throws Exception {
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);
        body.add("key", imgbbApiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        String url = imgbbApiUrl;
        ResponseEntity<ImgBBResponse> response = restTemplate.postForEntity(url, request, ImgBBResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().data.url;
        }
        throw new RuntimeException("ImgBB upload failed");
    }

    // Simplified response class
    private static class ImgBBResponse {
        public Data data;
        static class Data {
            public String url;
        }
    }
}
