package org.jeskey.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Entity
public class BoardAttach{

	@Id
	private String uuid;
	private String file_name;
	private int ord;

	@ManyToOne
	private Board board;

	public void changeBoard(Board board){
	    this.board = board;
	}
}
