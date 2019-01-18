package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.utils.CSVMessageConverterUtil;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
public class CSVMessageConverterUtilTest {

	CSVMessageConverterUtil<StockDTO> csvMessageConverterUtil = new CSVMessageConverterUtil<>();

	@Test
	public void testListToCsv() throws   IOException
	{
		List<StockDTO> stockList = new ArrayList<>();

		stockList.add(
				new StockDTO(1, 2, "Bread", "White", new BigDecimal(5), new BigDecimal(1), 2)
		);
		stockList.add(
				new StockDTO(1, 3, "Black Bread", "Black", new BigDecimal(6), new BigDecimal(2), 2)
		);
		stockList.add(
				new StockDTO(1, 4, "Graham Bread", "Graham", new BigDecimal(5), new BigDecimal(3), 2)
		);
		csvMessageConverterUtil.toCsv(StockDTO.class, stockList, new FileOutputStream("testStock.csv"));
	}

	@Test
	public void testCsvToList() throws IOException {
			InputStream inputStream = new FileInputStream(new File("testStock.csv"));
			System.out.println(new File(".").getAbsolutePath());
			List<StockDTO> stockDTOList = csvMessageConverterUtil.fromCsv(StockDTO.class, inputStream);
			assertEquals(3, stockDTOList.size());
			assertEquals(1, stockDTOList.get(0).getProductId().intValue());
			assertEquals(2, stockDTOList.get(0).getLocationId().intValue());
			assertEquals("Bread", stockDTOList.get(0).getProductName());
			assertEquals("White", stockDTOList.get(0).getProductDescription());
			assertEquals(new BigDecimal(5), stockDTOList.get(0).getProductPrice());
			assertEquals(new BigDecimal(1), stockDTOList.get(0).getProductWeight());
			assertEquals(2, stockDTOList.get(0).getQuantity());
	}
}
