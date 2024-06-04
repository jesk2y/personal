package org.jeskey.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class BoardAttachDTO {
	private String uuid;
	private String fileName;
	private String date;
}
