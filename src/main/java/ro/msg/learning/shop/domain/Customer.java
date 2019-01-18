package ro.msg.learning.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "customer_id")
	private Integer id;

	private String firstName;

	private String lastName;

	private String userName;

}
