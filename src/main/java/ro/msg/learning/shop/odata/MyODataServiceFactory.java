package ro.msg.learning.shop.odata;

import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.OrderService;

@RequiredArgsConstructor
public class MyODataServiceFactory extends ODataServiceFactory {
	private final CustomerRepository customerRepository;

	private final OrderService orderService;

	@Override
	public ODataService createService(ODataContext oDataContext) throws ODataException {
		EdmProvider edmProvider = new OrderServiceEdmProvider();
		ODataSingleProcessor singleProcessor = new OrderSingleProcessor(customerRepository, orderService);
		return createODataSingleProcessorService(edmProvider, singleProcessor);
	}
}
