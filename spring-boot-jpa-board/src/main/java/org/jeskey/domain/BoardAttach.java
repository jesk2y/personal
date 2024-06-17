package org.jeskey.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
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

	@Column(length = 255, nullable = false)
	private String fileName;

	@ColumnDefault("0")
	private int ord;

	@Column(length = 10, nullable = false)
	private String date;

	@ManyToOne
	private Board board;

	public void changeBoard(Board board){
	    this.board = board;
	}
}
