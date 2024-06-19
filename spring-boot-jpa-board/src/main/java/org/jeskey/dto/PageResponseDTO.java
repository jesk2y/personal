package org.jeskey.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageResponseDTO<E> {

	private double length = 7.0;	//페이지네이션 길이
	private int page, display, count;

	private int start, end;
	private boolean prev, next;

	private List<E> dtoList;

	@Builder
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

		this.page = pageRequestDTO.getPage();
		this.display = pageRequestDTO.getDisplay();

		this.page = page < 1 ? 1:page;

		this.count = total;
		this.dtoList = dtoList;

		this.end = (int)Math.ceil(this.page / length) * (int)length;
		this.start = this.end - (int)length + 1;

		int last = (int) Math.ceil(this.count / (double) display);

		this.end = end > last ? last:end;
		this.prev = this.start > 1;
		this.next = total > this.display * this.end;
	}

}
