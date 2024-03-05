package org.jeskey.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeskey.dto.UploadFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<UploadFileDTO> upload(@RequestPart("files") List<MultipartFile> uploadFile) {

		if(!uploadFile.isEmpty()) {

			final List<UploadFileDTO> list = new ArrayList<>();

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
						throw new IOException("이미지 파일만 등록가능합니다");
					}
				}catch(IOException e) {
					e.printStackTrace();
				}

				list.add(UploadFileDTO.builder()
							.uuid(uuid)
							.fileName(originalFileName)
							.build());
			});
			return list;
		}
		return null;
	}

	@Operation(summary="view 파일", description="GET 방식으로 첨부파일 조회")
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {

		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@Operation(summary="delete 파일", description="DELETE 방식으로 파일 삭제")
	@DeleteMapping("/delete/{fileName}")
	public Map<String, Boolean> removeFile(@PathVariable("fileName") String fileName){

		Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
		boolean removed = false;

		try {
			removed = resource.getFile().delete();	//true 리턴

			File thumbnailFile = new File(uploadPath+File.separator+"s_"+fileName);
			thumbnailFile.delete();

		}catch(Exception e){
			log.error(e.getMessage());
		}

		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("result",  removed);

		return resultMap;
	}
}
