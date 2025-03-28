package com.uet.hightex.controllers.support;

import com.uet.hightex.services.support.FileStorageService;
import com.uet.hightex.services.support.ImageBBService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private final FileStorageService fileStorageService;
    private final ImageBBService imageBBService;

    public FileController(FileStorageService fileStorageService, ImageBBService imageBBService) {
        this.fileStorageService = fileStorageService;
        this.imageBBService = imageBBService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> fileNames = fileStorageService.storeFiles(files);
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = fileStorageService.getContentType(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/upload-thumbnail-item")
    public ResponseEntity<String> uploadThumbnailImage(@RequestParam("file") MultipartFile file, @RequestParam("itemId") Long itemId) {
        String url = imageBBService.uploadAndSaveThumbnailImage(file, itemId);
        return ResponseEntity.ok("Thumbnail image uploaded successfully");
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = imageBBService.uploadAndGetLink(file);
        return ResponseEntity.ok(url);
    }
}
