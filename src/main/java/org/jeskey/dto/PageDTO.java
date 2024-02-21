package org.jeskey.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
public class PageDTO {

	private static double length = 7.0; // 페이지네이션 길이
	private int display = 15; // 한 페이지의 글 수

	private int start, end, page, count;
	private boolean prev, next;
	private Long bno;
	
	private String keyword, target;
	private String[] targets;

	private String link;
	
	public PageDTO() {
		this.page = 1;
		this.prev = false;
		this.next = false;
	}
	
	public void setTarget(String target) {
		this.target = target;
		
		if(target.length() == 0) {
			this.target = null;
			return;
		}
		
		this.targets = this.target.split("");
	}

	public void setCount(int count) {
		
		if(this.page < 1) {
			this.page = 1;
		}
		
		this.count = count;

		this.end = (int) (Math.ceil(this.page / length) * length);
		this.start = (int) (this.end - length + 1);

		if (display * this.end >= this.count) {

			this.end = (int) Math.ceil(this.count / (double) display) + 1;
			this.start = (int) (end == 1? 1 : this.end - length);
		}else{
			this.next = true;
		}

		if (this.start > 1) {
			this.prev = true;
		}else {
			this.start = 1;
		}
	}

	private int getSkip() {
		return (this.page - 1) * display;
	}
	
	public String getLink(Long bno) {
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
		
		if(bno != null && bno > 0) {
			uriComponentsBuilder.queryParam("bno", bno);
		}
		
		if(this.page > 0) {
			uriComponentsBuilder.queryParam("page", this.page);
		}
		
		if(this.target != null && this.keyword != null) {
			
			uriComponentsBuilder.queryParam("target", this.target)
			.queryParam("keyword", this.keyword);
		}
		
		this.link = uriComponentsBuilder.toUriString();
		
		return this.link;
	}
}
