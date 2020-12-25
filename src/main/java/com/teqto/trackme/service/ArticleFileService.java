package com.teqto.trackme.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleFileService {
	
	public void saveUploadedFiles(List<MultipartFile> files, String guid, String fileType) throws IOException;
	public Resource loadFileAsResource(String fileName);
	
}