package ro.msg.learning.shop.utils;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


@Component
public class CsvMessageConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {

	private CSVMessageConverterUtil csvMessageConverterUtil = new CSVMessageConverterUtil();

	public CsvMessageConverter() {
		super(new MediaType("text", "csv"));
	}

	@Override
	protected void writeInternal(List<T> objects, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Class<T> myClazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		csvMessageConverterUtil.toCsv(myClazz, objects, httpOutputMessage.getBody());
	}

	@Override
	protected List<T> readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		return csvMessageConverterUtil.fromCsv(aClass, httpInputMessage.getBody());
	}

	@Override
	public List<T> read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		return readInternal(aClass, httpInputMessage);
	}
}
