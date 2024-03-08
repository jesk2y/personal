package org.jeskey.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class FileDTO {
	private String uuid;
	private String fileName;
	private String date;
}
