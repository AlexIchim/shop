package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;

	private String name;

	private String description;

	private BigDecimal price;

	private BigDecimal weight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "category_id")
	private ProductCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "supplier_id")
	private Supplier supplier;

	@Override
	public String toString() {
		return "Product";
	}
}
