package ro.msg.learning.shop.exception;

public class OrderProcessingException extends  RuntimeException {
	public OrderProcessingException(String message) {
		super("OrderProcessingException: " + message);
	}
}
