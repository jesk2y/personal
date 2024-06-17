package org.jeskey.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	private Long bno;

	@NotEmpty(message = "제목을 입력해주세요")
	@Size(max = 50, message = "최대 50글자까지 입력할 수 있습니다")
	private String title;

	@NotEmpty(message = "내용을 입력해주세요")
	@Size(max = 5000, message = "최대 5000글자까지 입력할 수 있습니다")
	private String content;

	private LocalDateTime regdate;
	private LocalDateTime updatedate;

	private List<BoardAttachDTO> fileList;

	/*
	 * 컨트롤러에서 파라미터로 들어온 List<String> 타입의 파일 이름들을 BoardAttachDTO 타입으로 변환해서 집어넣는다.
	 * 이 과정은 자동으로 이루어진다
	 * 형식 : [date]/[uuid]/[파일이름]
	 */
	public void setFileNames(List<String> fileNames) {

		fileList = new ArrayList<>();

		fileNames.forEach(fileName -> {
			String[] arr = fileName.split("/");

			BoardAttachDTO file = BoardAttachDTO.builder()
			.date(arr[0])
			.uuid(arr[1])
			.fileName(arr[2]).build();

			fileList.add(file);
		});
	}

	/*
	 * private String user_id;
	 * private Long count_visit;

	 * public void setFileList(List<BoardAttachDTO> list){ this.fileList = list; }
	 * private int count_reply;
	 */
}
