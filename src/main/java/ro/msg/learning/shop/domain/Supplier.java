package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue
	@Column(name = "supplier_id")
	private Integer id;

	private String name;
}
