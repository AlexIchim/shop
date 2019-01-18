package ro.msg.learning.shop.exception;

public class LocationException extends RuntimeException {
	public LocationException(String message) {
		super("Error while processing location :" + message);
	}
}
