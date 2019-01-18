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
public class OrderDetail {
	@Id
	@Embedded
	private OrderDetailId id;

	private int quantity;
}
