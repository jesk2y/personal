package org.jeskey.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	@Column(length = 50, nullable = false)
	private String title;

	@Column(length = 5000, nullable = false)
	private String content;

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@Builder.Default
	private List<BoardAttach> fileList = new ArrayList<>();

	public void addFile(String date, String uuid, String fileName) {

		BoardAttach boardFile = BoardAttach.builder()
				.uuid(uuid)
				.file_name(fileName)
				.date(date)
				.board(this)
				.ord(fileList.size())
				.build();

		fileList.add(boardFile);
	}

	public void clearFiles() {

		fileList.forEach(boardImage -> boardImage.changeBoard(null));

		this.fileList.clear();
	}
	/*
	 * private Long count_visit; private String user_id; private List<BoardAttach>
	 * fileList; private int count_reply;
	 */
}
