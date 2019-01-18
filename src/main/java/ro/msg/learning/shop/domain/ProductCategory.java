package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ProductCategory {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer id;

	private String name;

	private String description;
}
