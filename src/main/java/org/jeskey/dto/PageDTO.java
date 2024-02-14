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

	private int start, end, page, count, bno;
	private boolean prev, next;
	
	private String keyword, type;
	private String[] types;

	private String link;
	
	public PageDTO() {
		this.page = 1;
		this.prev = false;
		this.next = false;
	}
	
	public void setType(String type) {
		this.type = type;
		
		if(type.length() == 0) {
			this.type = null;
			return;
		}
		
		this.types = this.type.split("");
		
	}

	public void setCount(int count) {
		
		if(this.page < 1) {
			this.page = 1;
		}
		
		this.count = count;

		this.end = (int) (Math.ceil(this.page / length) * length);
		this.start = (int) (this.end - length + 1);

		if (display * this.end >= this.count) {

			this.end = (int) Math.ceil(this.count / (double) display);
			this.start = (int) (this.end - length + 1);
		}else{
			this.next = true;
		}

		if (this.start != 1) {
			this.prev = true;
		}
		
		
	}

	private int getSkip() {
		System.out.println((this.page - 1) * display);
		return (this.page - 1) * display;
	}
	
	public String setLink(String path) {
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(path);
		
		if(page > 1) {
			System.out.println("hi");
			uriComponentsBuilder.queryParam("page", this.page);
		}
		
		if(type != null || keyword != null) {
			
			uriComponentsBuilder.queryParam("type", this.type)
			.queryParam("keyword", this.keyword);
		}
		
		this.link = uriComponentsBuilder.toUriString();
		
		return this.link;
		
	}
}
