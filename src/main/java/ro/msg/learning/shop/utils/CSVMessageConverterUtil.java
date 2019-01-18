package ro.msg.learning.shop.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Component
public class CSVMessageConverterUtil<T> {

	public List<T> fromCsv(Class<T> clazz, InputStream inputStream) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema csvSchema = csvMapper.schemaFor(clazz);
		MappingIterator<T> mappingIterator = csvMapper.readerFor(clazz).with(csvSchema).readValues(inputStream);
		return mappingIterator.readAll();
	}

	public void toCsv(Class<T> clazz, List<T> pojoObjects, OutputStream outputStream) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);


		CsvSchema csvSchema = csvMapper.schemaFor(clazz);
		ObjectWriter objectWriter = csvMapper.writer(csvSchema);
		objectWriter.writeValue(outputStream, pojoObjects);
	}
}
