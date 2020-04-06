package com.sages4it.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOG = org.slf4j.LoggerFactory.getLogger(S3Service.class);

	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Iniciando Upload");
			s3Client.putObject(new PutObjectRequest(bucketName, "teste", file));
			LOG.info("Upload finalizado");
		} catch (AmazonS3Exception e) {
			LOG.info("AmazonS3Exception: " + e.getMessage());
			LOG.info("Status code: " + e.getErrorCode());
		}catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}
	}
}
