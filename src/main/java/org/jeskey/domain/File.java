package org.jeskey.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class File{

	private String uuid;
	private String file_name;
	private int ord;
	private String date;

	private Long bno;

	public void setBno(Long bno) {
		this.bno = bno;
	}
}
