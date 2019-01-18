package ro.msg.learning.shop.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Integer id;

	private String name;

	@NotNull
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "id.location", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@ToString.Exclude
	private List<Stock> stocks;

	@OneToMany(mappedBy = "location")
	@ToString.Exclude
	private List<Revenue> revenues;
}
