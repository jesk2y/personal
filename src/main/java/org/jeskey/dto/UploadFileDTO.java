package org.jeskey.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadFileDTO {
	private String uuid;
	private String fileName;

	public String getLink() {

		return "s_"+uuid+"_"+fileName;
	}
}
