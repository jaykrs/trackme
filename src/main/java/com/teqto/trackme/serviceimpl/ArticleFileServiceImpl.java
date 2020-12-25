package com.teqto.trackme.serviceimpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.teqto.trackme.exception.MyFileNotFoundException;
import com.teqto.trackme.service.ArticleFileService;
import com.teqto.trackme.utils.OsUtils;

@Repository
@Transactional
public class ArticleFileServiceImpl implements ArticleFileService{

	@Autowired
	private Environment env;
	
	private Path fileStorageLocation;
	

	// save file
		public void saveUploadedFiles(List<MultipartFile> files, String guid, String fileType) throws IOException {
			String UPLOADED_FOLDER = Strings.EMPTY;
			System.out.println("Single SYS file upload with userId!saveUploadedFiles  : "+OsUtils.getOperatingSystemType().toString() + files.size());
			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue; // next pls
				}
				System.out.println("Single SYS file upload with userId!saveUploadedFiles  : "+file.getOriginalFilename());
				byte[] bytes = file.getBytes();
				if(OsUtils.getOperatingSystemType().toString().equals("Windows")) {
					UPLOADED_FOLDER = fileType.equalsIgnoreCase("avatar")?env.getProperty("userBucket.window.path"):env.getProperty("userBucket.window.path.group");
				}
				else if(OsUtils.getOperatingSystemType().toString().equals("Linux")) {
					UPLOADED_FOLDER = fileType.equalsIgnoreCase("avatar")?env.getProperty("userBucket.linux.path"):env.getProperty("userBucket.linux.path.group");
				}
				
				else if(OsUtils.getOperatingSystemType().toString().equals("MacOS")) {
					UPLOADED_FOLDER = env.getProperty("userBucket.macos.path");
				}
				System.out.println("Single SYS file upload with userId!saveUploadedFiles to folder  : "+UPLOADED_FOLDER);
				
			String fileName = fileType.equalsIgnoreCase("avatar")
					? Strings.isNotBlank(guid) ? guid + "_avatar.png" : file.getOriginalFilename()
					: guid;
				System.out.println("Single SYS file upload with userId!saveUploadedFiles to folder  : "+UPLOADED_FOLDER+fileName);
				
				Path path = Paths.get(UPLOADED_FOLDER + fileName);
				Files.write(path, bytes);

			}

		}
		
		public Resource loadFileAsResource(String fileName) {
	       
			if(OsUtils.getOperatingSystemType().toString().equals("Windows")) {
				this.fileStorageLocation = Paths.get(env.getProperty("userBucket.window.path"))
		                .toAbsolutePath().normalize();
			}
			else if(OsUtils.getOperatingSystemType().toString().equals("Linux")) {
				this.fileStorageLocation = Paths.get(env.getProperty("userBucket.linux.path"))
		                .toAbsolutePath().normalize();
			}
			
			else if(OsUtils.getOperatingSystemType().toString().equals("MacOS")) {
				this.fileStorageLocation = Paths.get(env.getProperty("userBucket.macos.path"))
		                .toAbsolutePath().normalize();
			}
			
			try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MyFileNotFoundException("File not found " + fileName, ex);
	        }
	    }
}