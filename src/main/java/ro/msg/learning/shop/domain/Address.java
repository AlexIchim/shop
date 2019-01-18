package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private String country;

	private String county;

	private String city;

	private String streetAddress;
}
