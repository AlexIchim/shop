package ro.msg.learning.shop.exception;

public class OrderException extends Exception {
	public OrderException(String message) {
		super("Error while processing order :" + message);
	}
}
