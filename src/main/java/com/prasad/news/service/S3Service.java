package com.prasad.news.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.prasad.news.config.S3config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private S3config s3config;

    public void uploadFile(final String bucketName,
                           final String keyName,
                           final Long contentLength,
                           final String contentType,
                           final InputStream value
    ) throws AmazonClientException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType);

        s3config.s3Client().putObject(bucketName, keyName, value, metadata);
    }
}
