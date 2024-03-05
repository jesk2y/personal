package org.jeskey.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

	private Set<BoardImage> imageSet = new HashSet<>();

	public void addImage(String uuid, String fileName) {

		BoardImage boardImage = BoardImage.builder()
				.uuid(uuid)
				.fileName(fileName)
				.bno(this.bno)
				.ord(imageSet.size())
				.build();

		imageSet.add(boardImage);
	}

}
