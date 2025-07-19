package com.CustomerRelationshipManagement.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/";

    public List<String> saveFiles(MultipartFile[] files) throws IOException {
        new File(uploadDir).mkdirs();
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, file.getBytes());
            urls.add("/" + uploadDir + fileName);
        }
        return urls;
    }
}
