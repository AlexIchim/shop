package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfo {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	private int quantity;

	private LocalDateTime shippingDate;

	public ShippingInfo(Order order, Product product, Location location, int quantity, LocalDateTime shippingDate) {
		this.order = order;
		this.product = product;
		this.location = location;
		this.quantity = quantity;
		this.shippingDate = shippingDate;
	}

}
