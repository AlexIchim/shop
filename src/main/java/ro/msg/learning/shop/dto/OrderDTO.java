package ro.msg.learning.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Address;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private LocalDateTime date;
	private Address address;
	private List<ProductDTO> products;
}

