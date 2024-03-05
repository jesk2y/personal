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
public class BoardImage implements Comparable<BoardImage>{

	private String uuid;

	private String fileName;

	private int ord;

	private Long bno;

	@Override
	public int compareTo(BoardImage other) {

		return this.ord - other.ord;
	}
}
