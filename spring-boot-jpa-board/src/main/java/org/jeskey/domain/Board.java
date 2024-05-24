package org.jeskey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	@Column(length = 50, nullable = false)
	private String title;

	@Column(length = 5000, nullable = false)
	private String content;

	/* private Long count_visit;
	 * private String user_id;
	 * private List<BoardAttach> fileList;
	 * private int count_reply;
	 */
}
