package org.jeskey.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter	//파라미터 자동수집
public class TodoDTO {
	
	private Long tno;
	private String content;
	private boolean finished;
	
}
