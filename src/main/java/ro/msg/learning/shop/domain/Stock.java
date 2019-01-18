package ro.msg.learning.shop.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Stock {

	@Id
	@Embedded
	private StockId id;

	private int quantity;
}
