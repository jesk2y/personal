package org.jeskey.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class FileController {

	@Value("${org.jeskey.upload.path}")
	private String uploadPath;

	@Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
	@PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String upload(@RequestPart("files") List<MultipartFile> uploadFile) {

		if(!uploadFile.isEmpty()) {
			uploadFile.forEach(multipartFile -> {
				String originalFileName = multipartFile.getOriginalFilename();

				String uuid = UUID.randomUUID().toString();

				Path savePath = Paths.get(uploadPath, uuid+ "_" +originalFileName);

				try {
					if(Files.probeContentType(savePath).startsWith("image")){

						multipartFile.transferTo(savePath);

						//섬네일
						File thumbFile = new File(uploadPath, "s_"+ uuid + "_" + originalFileName);
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
					}else {
						throw new IOException();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			});
		}

		return null;
	}
}
