package com.mdkj.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/** minio工具 */
@Slf4j
public class MinioUtil {
    /** Minio客户端 */
    private static MinioClient minioClient;
    /** Minio端点 */
    private static String endpoint;

    /**
     * 由配置类注入MinioClient实例
     */
    public static void setMinioClient(MinioClient client) {
        minioClient = client;
    }

    /**
     * 由配置类注入Minio端点
     */
    public static void setEndpoint(String minioEndpoint) {
        endpoint = minioEndpoint;
    }

    /**
     * 将文件上传到MinIO对象存储服务中
     *
     * @param file       文件对象
     * @param fileName   文件名称
     * @param dir        文件存储目录
     * @param bucketName MinIO桶名
     */
    @SneakyThrows
    public static void upload(MultipartFile file,
                              String fileName,
                              String dir,
                              String bucketName) {
        checkMultipartFile(file);
        checkFileName(fileName);
        checkDir(dir);
        checkBucket(bucketName);
        // 使用putObject上传文件到指定Bucket
        minioClient.putObject(bucketName, dir + "/" + fileName,
                file.getInputStream(),
                file.getContentType());
    }

    /**
     * 根据文件对象获取随机文件名称
     *
     * @param file 文件对象
     * @return 随机文件名称，格式为 "UUID后10位 + 当前时间戳 + 原始文件后缀"
     */
    public static String randomFilename(MultipartFile file) {
        checkMultipartFile(file);
        // 获取原始文件名
        String fileName = file.getOriginalFilename();
        // 如果原始文件名为空，使用默认名称
        if (StrUtil.isEmpty(fileName)) {
            fileName = "default-avatar.jpg";
        }
        // 获取文件后缀
        String suffix = ".jpg";
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            suffix = fileName.substring(lastDotIndex);
        }
        // 生成随机文件名并返回
        return UUID.randomUUID().toString().substring(26) + "-"
                + System.currentTimeMillis()
                + suffix;
    }

    /**
     * 从MinIO中删除指定文件
     *
     * @param fileName   文件名
     * @param dir        文件存储目录
     * @param bucketName MinIO桶名
     */
    @SneakyThrows
    public static void delete(String fileName,
                              String dir,
                              String bucketName) {
        checkFileName(fileName);
        checkDir(dir);
        checkBucket(bucketName);
        minioClient.removeObject(bucketName, dir + "/" + fileName);
    }

    /**
     * 构建公开访问URL
     */
    public static String publicUrl(String fileName, String dir, String bucketName) {
        checkFileName(fileName);
        checkDir(dir);
        checkBucket(bucketName);
        if (StrUtil.isBlank(endpoint)) {
            throw new RuntimeException("MINIO端点未初始化，请检查MinIO配置");
        }
        return endpoint + "/" + bucketName + "/" + dir + "/" + fileName;
    }

    /**
     * 检查 MultipartFile 是否为空，若为空则抛出异常
     *
     * @param multipartFile 文件对象
     */
    private static void checkMultipartFile(MultipartFile multipartFile) {
        if (ObjectUtil.isNull(multipartFile)) {
            throw new RuntimeException("MINIO文件对象为空");
        }
    }

    /**
     * 检查MinioClient是否初始化成功
     */
    private static void checkClient() {
        if (ObjectUtil.isNull(minioClient)) {
            throw new RuntimeException("MINIO客户端未初始化，请检查MinIO配置");
        }
    }

    /**
     * 检查文件名称是否为空或空串，若为空或空串则抛出异常
     *
     * @param fileName 文件名称
     */
    private static void checkFileName(String fileName) {
        if (StrUtil.isEmpty(fileName)) {
            throw new RuntimeException("MINIO文件名为空或空串");
        }
    }

    /**
     * 检查文件上传目录名称是否为空或空串，若为空或空串则抛出异常
     *
     * @param dir 文件上传目录名称
     */
    private static void checkDir(String dir) {
        if (StrUtil.isEmpty(dir)) {
            throw new RuntimeException("MINIO文件上传目录名称为空或空串");
        }
    }

    /**
     * 检查MINIO桶是否存在，桶名为空或空串，或桶不存在均抛出异常
     *
     * @param bucketName MINIO桶
     */
    @SneakyThrows
    private static void checkBucket(String bucketName) {
        checkClient();

        if (StrUtil.isEmpty(bucketName)) {
            throw new RuntimeException("MINIO桶名为空或空串");
        }

        // 校验数据桶是否存在
        if (!minioClient.bucketExists(bucketName)) {
            throw new RuntimeException("MinIO桶不存在");
        }
    }
}