package org.jeskey.dto;

import java.time.LocalDateTime;
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

	private Long count_visit;

	private LocalDateTime regdate;

	private String user_id;

	private List<String> fileNames;	//컨트롤러에서 받아올 때
	private List<BoardAttachDTO> fileList;	//컨트롤러로 보낼 때

	public void setFileList(List<BoardAttachDTO> list){
		this.fileList = list;
	}

	private int count_reply;
}
