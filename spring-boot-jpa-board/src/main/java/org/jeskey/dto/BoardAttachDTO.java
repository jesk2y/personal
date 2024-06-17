package org.jeskey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardAttachDTO {
	private String uuid;
	private String fileName;
	private String date;
}
