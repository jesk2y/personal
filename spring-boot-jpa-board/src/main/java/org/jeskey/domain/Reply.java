package org.jeskey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="Reply", indexes = {@Index(name="idx_reply_board_bno", columnList = "board_bno")})
public class Reply extends BaseEntity{

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long rno;

    @Column(length = 1000, nullable = false)
	private String replyText;

    @ManyToOne
	private Board board;

    public void changeText(String replyText){
        this.replyText = replyText;
    }
}
