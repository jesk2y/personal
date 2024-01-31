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
@Setter	//�Ķ���� �ڵ�����
public class TodoDTO {
	
	private Long tno;
	private String content;
	private boolean finished;
	
}
