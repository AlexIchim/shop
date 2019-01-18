package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;

	@OneToMany(mappedBy = "order")
	private List<ShippingInfo> shippedFrom;

	@OneToMany(mappedBy = "id.order",
			cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<OrderDetail> orderDetails;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Embedded
	private Address address;

	private LocalDateTime date;


	public Order(Customer customer, Address address, LocalDateTime date) {
		this.customer = customer;
		this.address = address;
		this.date = date;
	}
}
