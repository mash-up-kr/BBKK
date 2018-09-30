package org.seoul.kk.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "bbkk.aws.s3")
@PropertySource(value = { "classpath:/s3/aws-s3.properties" })
@AllArgsConstructor
@NoArgsConstructor
@Data
public class S3Properties {

    private String accessKey;
    private String secretKey;
    private String bucketName;

}
