package com.mdkj.config;

import com.mdkj.constant.ML;
import com.mdkj.util.MinioUtil;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Slf4j
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint:http://192.168.211.132:9000}")
    private String endpoint;
    @Value("${minio.access-key:minioadmin}")
    private String accessKey;
    @Value("${minio.secret-key:minioadmin}")
    private String secretKey;

    @PostConstruct
    public void init() {
        try {
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            MinioUtil.setMinioClient(minioClient);
            MinioUtil.setEndpoint(endpoint);
            if (!minioClient.bucketExists(ML.MinIO.BUCKET_NAME)) {
                minioClient.makeBucket(ML.MinIO.BUCKET_NAME);
                log.info("MinIO Bucket [{}] 创建成功", ML.MinIO.BUCKET_NAME);
            } else {
                log.info("MinIO Bucket [{}] 已存在", ML.MinIO.BUCKET_NAME);
            }
            log.info("MinIO 客户端初始化成功 endpoint={}", endpoint);
        } catch (Exception e) {
            log.error("MinIO初始化失败 endpoint={} accessKey={} error={}", endpoint, accessKey, e.getMessage());
        }
    }
}