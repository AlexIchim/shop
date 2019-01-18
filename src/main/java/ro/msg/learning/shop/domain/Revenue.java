package ro.msg.learning.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Revenue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	private LocalDateTime date;

	private BigDecimal sum;

	public Revenue(Location location, LocalDateTime date, BigDecimal sum) {
		this.location = location;
		this.date = date;
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Revenue{" +
				"id=" + id +
/*
				", location=" + location +
*/
				", date=" + date +
				", sum=" + sum +
				'}';
	}
}
