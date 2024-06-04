package org.jeskey.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int display = 15;

    private int bno;
    
	private String keyword, target;
	private String[] targets;

	public Pageable getPageable() {
		return PageRequest.of(this.page-1, this.display, Sort.by("bno").descending());
	}

	public void setTarget(String target) {
		this.target = target;

		if(target.length() == 0) {
			this.target = null;
			return;
		}

		this.targets = this.target.split("");
	}

	private String link;

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
