package com.CustomerRelationshipManagement.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.*;

@Service
public class FileStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public FileStorageService(
            @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretKey)))
                .build();
    }

    public List<String> saveFiles(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String key = UUID.randomUUID() + "_" + Objects.requireNonNull(file.getOriginalFilename());

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")  // Optional, for public files only
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + key;
            urls.add(fileUrl);
        }

        return urls;
    }
}
