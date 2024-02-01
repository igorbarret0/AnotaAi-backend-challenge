package com.igorbarreto.anotaaichallenge.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {
    @Value("${aws.region}")
    private String region;
    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.sns.topic.catalog.arn}")
    private String snsCatalog;

    @Bean
    public AmazonSNS amazonSNSBuilder() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();

    }

    @Bean(name = "catalogEventsTopic")
    public Topic snsCatalogTopicBuilder() {
        return new Topic().withTopicArn(snsCatalog);
    }

}
