package org.jeskey.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {

	private Long bno;
	private String title;
	private String content;
	private Long count_visit;
	private LocalDateTime regdate;

	private String user_id;
	/*
	 * @Builder.Default private List<File> fileList = new arrayList<>();
	 *
	 * public void addFile(String uuid, String fileName, String date) {
	 *
	 * File file = File.builder() .uuid(uuid) .file_name(fileName) .date(date)
	 * .bno(this.bno) .ord(fileList.size()) .build();
	 *
	 * fileList.add(file); }
	 */

}
