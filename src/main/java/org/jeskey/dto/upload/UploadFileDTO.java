package org.jeskey.dto.upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class UploadFileDTO {

	private List<MultipartFile> files;

}
