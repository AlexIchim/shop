package ro.msg.learning.shop.odata;

import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetSimplePropertyUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.OrderService;

import java.io.InputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class OrderSingleProcessor extends ODataSingleProcessor {


	private final CustomerRepository customerRepository;

	private final OrderService orderService;

	static final String ENTITY_SET_NAME_ORDERS = "Orders";
	static final String ENTITY_NAME_ORDER = "Order";

	static final String ENTITY_SET_NAME_CUSTOMERS = "Customers";
	static final String ENTITY_NAME_CUSTOMER= "Customer";

	static final String ENTITY_SET_NAME_PRODUCTS = "Products";
	static final String ENTITY_NAME_PRODUCT = "Product";


	static final String ENTITY_SET_NAME_ORDERDETIALS = "OrderDetails";
	static final String ENTITY_NAME_ORDERDETAIL = "OrderDetail";

	@Override
	public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType) throws ODataException {
		if (uriInfo.getNavigationSegments().size() == 0) {
			EdmEntitySet entitySet = uriInfo.getStartEntitySet();

			if (ENTITY_SET_NAME_CUSTOMERS.equals(entitySet.getName())) {
				int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
				Map<String, Object> data = StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toMap(e -> e.getFirstName(), e -> e.getId()));

				if (data != null) {
					URI serviceRoot = getContext().getPathInfo().getServiceRoot();
					ODataEntityProviderPropertiesBuilder propertiesBuilder = EntityProviderWriteProperties.serviceRoot(serviceRoot);

					return EntityProvider.writeEntry(contentType, entitySet, data, propertiesBuilder.build());
				}
			}
			throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
		}
		throw new ODataNotImplementedException();
	}

	@Override
	public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {
		EdmEntitySet entitySet;
		if (uriInfo.getNavigationSegments().size() == 0) {
			entitySet = uriInfo.getStartEntitySet();
			if (ENTITY_SET_NAME_CUSTOMERS.equals(entitySet.getName())) {
				List<Customer> data = customerRepository.findAll();
				List<Map<String, Object>> contentData = new ArrayList<Map<String, Object>>();
				for (Customer customer: data
					 ) {
					Map<String, Object> myObj = new HashMap<String, Object>();
					myObj.put("Id", customer.getId());
					myObj.put("FirstName", customer.getFirstName());
					myObj.put("LastName", customer.getLastName());
					myObj.put("UserName", customer.getUserName());
					contentData.add(myObj);
				}

				return EntityProvider.writeFeed(contentType, entitySet, contentData, EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
			}

			throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
		}
		return super.readEntitySet(uriInfo, contentType);
	}

	@Override
	public ODataResponse createEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType) throws ODataException {
		if (uriInfo.getNavigationSegments().size() > 0) {
			throw new ODataNotImplementedException();
		}

		//No support for media resources
		if (uriInfo.getStartEntitySet().getEntityType().hasStream()) {
			throw new ODataNotImplementedException();
		}

		EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();
		ODataEntry entry = EntityProvider.readEntry(requestContentType, uriInfo.getStartEntitySet(), content, properties);
		Map<String, Object> data = entry.getProperties();

		List<ProductDTO> productDTOS = (List<ProductDTO>) data.get("OrderDetails");
		LocalDateTime orderTime = (LocalDateTime) data.get("Date");
		Address address = (Address) data.get("Address");
		orderService.ceateOrder(new OrderDTO(orderTime, address, productDTOS));

		return EntityProvider.writeEntry(contentType, uriInfo.getStartEntitySet(), entry.getProperties(), EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());

	}

	private int getKeyValue(KeyPredicate key) throws ODataException {
		EdmProperty property = key.getProperty();
		EdmSimpleType type = (EdmSimpleType) property.getType();
		return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), Integer.class);
	}
}
