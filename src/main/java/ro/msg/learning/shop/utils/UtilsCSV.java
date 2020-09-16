package ro.msg.learning.shop.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class UtilsCSV<T> {
    //generic type param T representing the type of POJO, in our case representing the Stock entity
    public static <T> List<T> fromCvs(Class<T> csvclass, InputStream inputStream) throws IOException {
        //mapper and schema always
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(csvclass);
        MappingIterator<T> it = mapper
                .readerFor(csvclass)
                .with(schema)
                .readValue(inputStream);
        return it.readAll();
    }

    public static <T> void toCsv(Class<?> tClass, List<Object> listItem, OutputStream outputStream) throws IOException {
        outputStream.write(listItem.toString().getBytes());
    }
}

