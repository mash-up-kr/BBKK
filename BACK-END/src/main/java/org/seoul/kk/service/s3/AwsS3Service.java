package org.seoul.kk.service.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.errorprone.annotations.DoNotCall;
import lombok.extern.slf4j.Slf4j;
import org.seoul.kk.config.properties.S3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Slf4j
@Service
public class AwsS3Service {

    private static final String S3_HOST = "https://s3.ap-northeast-2.amazonaws.com";

    private S3Properties s3Properties;
    private final AmazonS3 s3Client;

    @Autowired
    public AwsS3Service(S3Properties s3Properties) {
        this.s3Properties = s3Properties;
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey())))
                .build();

    }

    public String uploadFile(String key, File file) {
        try {
            log.info("upload file to aws s3 bucketName: {}, key: {}", s3Properties.getBucketName(), key);
            PutObjectRequest objectRequest = new PutObjectRequest(s3Properties.getBucketName(), key, file);
            objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(objectRequest);
        } catch (SdkClientException e) {
            log.info("upload file fail : {}", Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }

        return S3_HOST + "/" + s3Properties.getBucketName() + "/" + key;
    }

    public void deleteFile(String key) {
        log.info("delete file from aws s3 bucketName: {}, key: {}", s3Properties.getBucketName(), key);
        try {
            s3Client.deleteObject(s3Properties.getBucketName(), key);
        } catch (SdkClientException e) {
            log.info("delete file fail : {}", Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }
    }

    //TODO 업로드 완료시 까지 스레드를 block시키는 메서드를 구현합니다.
    @DoNotCall
    public void uploadFileWaitForComplete(String key, File file) {
    }

}
