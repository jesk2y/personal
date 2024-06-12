package org.jeskey.controller;

import java.nio.file.Files;
import java.util.List;

import org.jeskey.common.FileUtils;
import org.jeskey.dto.BoardAttachDTO;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

	private final FileUtils fileUtils;

	@Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
	@PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<BoardAttachDTO> upload(@RequestPart("file") List<MultipartFile> uploadFile) {

		return fileUtils.uploadFiles(uploadFile);
	}

	@Operation(summary = "Upload POST", description = "DELETE 방식으로 파일 삭제")
	@DeleteMapping("/delete/{date}/{fileName}")
	public void delete(@PathVariable("date") String date, @PathVariable("fileName") String fileName) {

		fileUtils.deleteFile(date, fileName);

	}

	@Operation(summary="view 파일", description="GET 방식으로 첨부파일 조회")
	@GetMapping("/view/{date}/{fileName}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable("date") String date, @PathVariable("fileName") String fileName) {

		System.out.println(fileUtils.getPath(date, fileName));

		Resource resource = new FileSystemResource(fileUtils.getPath(date, fileName));

		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.ok().headers(headers).body(resource);
	}

}
