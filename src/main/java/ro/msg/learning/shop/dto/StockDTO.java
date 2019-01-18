package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StockDTO {
	private Integer productId;
	private Integer locationId;
	private String productName;
	private String productDescription;
	private BigDecimal productPrice;
	private BigDecimal productWeight;
	private int quantity;
}
