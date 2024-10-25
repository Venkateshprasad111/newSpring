package com.prasad.news.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.gson.Gson;
import com.prasad.news.model.AwsSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

//@Configuration
public class RdsSecret {
    @Value("${aws.region}")
    private String region;

//    @Value("${aws.accesskey}")
//    private String accessKey;
//
//    @Value("${aws.secretkey}")
//    private String secretKey;

    private Gson gson=new Gson();

//    @Bean
//    public DataSource dataSource() {
//        AwsSecret secrets = getSecret();
//        return DataSourceBuilder
//                .create()
//                //  .driverClassName("com.mysql.cj.jdbc.driver")
//                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/news")
//                .username(secrets.getUsername())
//                .password(secrets.getPassword())
//                .build();
//    }

    public AwsSecret getSecret() {

        String secretName = "dev/news/newssecret";
        try {

//            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
            SecretsManagerClient client = SecretsManagerClient.builder()
                    .region(Region.of(region))
//                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                    .build();


        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
            System.out.println("Inside try catch");
        GetSecretValueResponse getSecretValueResponse;


        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
            String secret = getSecretValueResponse.secretString();
            System.out.println("Secret"+secret);
            return gson.fromJson(secret, AwsSecret.class);
        } catch (Exception e) {

            throw e;
        }


        // Your code goes here.
    }
}
