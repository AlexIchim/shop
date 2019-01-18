package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.api.edm.*;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.apache.olingo.odata2.api.exception.ODataException;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceEdmProvider extends EdmProvider {
	static final String ENTITY_SET_NAME_ORDERS = "Orders";
	static final String ENTITY_NAME_ORDER = "Order";

	static final String ENTITY_SET_NAME_CUSTOMERS = "Customers";
	static final String ENTITY_NAME_CUSTOMER= "Customer";

	static final String ENTITY_SET_NAME_PRODUCTS = "Products";
	static final String ENTITY_NAME_PRODUCT = "Product";


	static final String ENTITY_SET_NAME_ORDERDETIALS = "OrderDetails";
	static final String ENTITY_NAME_ORDERDETAIL = "OrderDetail";

	private static final String NAMESPACE = "ro.msg.learning.shop.domain";

	private static final FullQualifiedName ENTITY_ORDER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER);
	private static final FullQualifiedName ENTITY_CUSTOMER= new FullQualifiedName(NAMESPACE, ENTITY_NAME_CUSTOMER);
	private static final FullQualifiedName ENTITY_PRODUCT = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCT);
	private static final FullQualifiedName ENTITY_ORDERDETAIL= new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDERDETAIL);

	private static final FullQualifiedName COMPLEX_ADDRESS_TYPE = new FullQualifiedName(NAMESPACE, "Address");


	private static final FullQualifiedName ASSOCIATION_ORDER_CUSTOMER= new FullQualifiedName(NAMESPACE, "Order_Customer_Customer_Orders");
	private static final FullQualifiedName ASSOCIATION_ORDERDETAIL_ORDER = new FullQualifiedName(NAMESPACE, "OrderDetail_Order_Order_OrderDetails");
	private static final FullQualifiedName ASSOCIATION_ORDERDETAIL_PRODUCT = new FullQualifiedName(NAMESPACE, "OrderDetail_Product_Products_OrderDetail");

	private static final String ROLE_ORDER_CUSTOMER = "Order_Customer";
	private static final String ROLE_CUSTOMER_ORDERS = "Customer_Orders";
	private static final String ROLE_ORDERDETAIL_ORDER = "OrderDetail_Order";
	private static final String ROLE_ORDER_ORDERDETAILS = "Order_OrderDetails";
	private static final String ROLE_ORDERDETAIL_PRODUCT = "OrderDetail_Product";
	private static final String ROLE_PRODUCTS_ORDERDETAIL= "Products_OrderDetail";

	private static final String ENTITY_CONTAINER = "ODataOrdersEntityContainer";

	@Override
	public List<Schema> getSchemas() throws ODataException {
		List<Schema> schemas = new ArrayList<Schema>();

		Schema schema = new Schema();
		schema.setNamespace(NAMESPACE);

		List<EntityType> entityTypes = new ArrayList<EntityType>();
		entityTypes.add(getEntityType(ENTITY_ORDER));
		entityTypes.add(getEntityType(ENTITY_CUSTOMER));
		entityTypes.add(getEntityType(ENTITY_PRODUCT));
		entityTypes.add(getEntityType(ENTITY_ORDERDETAIL));
		schema.setEntityTypes(entityTypes);

		List<ComplexType> complexTypes = new ArrayList<ComplexType>();
		complexTypes.add(getComplexType(COMPLEX_ADDRESS_TYPE));
		schema.setComplexTypes(complexTypes);

		List<Association> associations = new ArrayList<Association>();
		associations.add(getAssociation(ASSOCIATION_ORDER_CUSTOMER));
		associations.add(getAssociation(ASSOCIATION_ORDERDETAIL_ORDER));
		associations.add(getAssociation(ASSOCIATION_ORDERDETAIL_PRODUCT));
		schema.setAssociations(associations);

		List<EntityContainer> entityContainers = new ArrayList<EntityContainer>();
		EntityContainer entityContainer = new EntityContainer();
		entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

		List<EntitySet> entitySets = new ArrayList<EntitySet>();
		entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERS));
		entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_CUSTOMERS));
		entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERDETIALS));
		entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCTS));
		entityContainer.setEntitySets(entitySets);

		schema.setEntityContainers(entityContainers);
		schemas.add(schema);
		return schemas;
	}

	@Override
	public EntityType getEntityType(FullQualifiedName edmFQName) throws ODataException {
		if (NAMESPACE.equals(edmFQName.getNamespace())) {
			if (ENTITY_CUSTOMER.getName().equals(edmFQName.getName())) {
				return getCustomerEntityType();
			}
			if (ENTITY_ORDER.getName().equals(edmFQName.getName())) {
				return getOrderEntityType();
			}
			if (ENTITY_ORDERDETAIL.getName().equals(edmFQName.getName())) {
				return getOrderDetailEntityType();
			}
			if (ENTITY_PRODUCT.getName().equals(edmFQName.getName())) {
				return getProductEntityType();
			}
		}
		return null;
	}

	public EntityType getProductEntityType() {
		List<Property> properties = new ArrayList<Property>();

		properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
		properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setMaxLength(40)));
		properties.add(new SimpleProperty().setName("Description").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setMaxLength(40)));
		properties.add(new SimpleProperty().setName("Price").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));
		properties.add(new SimpleProperty().setName("Weight").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));


		//Key
		List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
		keyProperties.add(new PropertyRef().setName("Id"));
		Key key = new Key().setKeys(keyProperties);

		return new EntityType().setName(ENTITY_PRODUCT.getName())
				.setProperties(properties)
				.setKey(key);
	}

	public EntityType getOrderDetailEntityType() {
		List<Property> properties = new ArrayList<Property>();
		properties.add(new SimpleProperty().setName("Quantity").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));

		//Navigation Properties
		List<NavigationProperty> navigationProperties = new ArrayList<NavigationProperty>();
		navigationProperties.add(new NavigationProperty().setName("Order")
				.setRelationship(ASSOCIATION_ORDERDETAIL_ORDER).setFromRole(ROLE_ORDERDETAIL_ORDER).setToRole(ROLE_ORDER_ORDERDETAILS));
		navigationProperties.add(new NavigationProperty().setName("Product")
				.setRelationship(ASSOCIATION_ORDERDETAIL_PRODUCT).setFromRole(ROLE_ORDERDETAIL_PRODUCT).setToRole(ROLE_PRODUCTS_ORDERDETAIL));


		//Key
		List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
		keyProperties.add(new PropertyRef().setName("Order"));
		keyProperties.add(new PropertyRef().setName("Product"));
		Key key = new Key().setKeys(keyProperties);

		return new EntityType().setName(ENTITY_ORDERDETAIL.getName())
				.setProperties(properties)
				.setNavigationProperties(navigationProperties)
				.setKey(key);
	}

	public EntityType getOrderEntityType() {
		List<Property> properties = new ArrayList<Property>();
		properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
		properties.add(new ComplexProperty().setName("Address").setType(new FullQualifiedName(NAMESPACE, "Address")));
		properties.add(new SimpleProperty().setName("Date").setType(EdmSimpleTypeKind.DateTime)
				.setFacets(new Facets().setNullable(false).setConcurrencyMode(EdmConcurrencyMode.Fixed))
				.setCustomizableFeedMappings(new CustomizableFeedMappings().setFcTargetPath(EdmTargetPath.SYNDICATION_UPDATED)));

		//Navigation Properties
		List<NavigationProperty> navigationProperties = new ArrayList<NavigationProperty>();
		navigationProperties.add(new NavigationProperty().setName("Customer")
				.setRelationship(ASSOCIATION_ORDER_CUSTOMER).setFromRole(ROLE_ORDER_CUSTOMER).setToRole(ROLE_CUSTOMER_ORDERS));
		navigationProperties.add(new NavigationProperty().setName("OrderDetails").setRelationship(ASSOCIATION_ORDERDETAIL_ORDER).setFromRole(ROLE_ORDERDETAIL_ORDER).setToRole(ROLE_ORDER_ORDERDETAILS));

		//Key
		List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
		keyProperties.add(new PropertyRef().setName("Id"));
		Key key = new Key().setKeys(keyProperties);

		return new EntityType().setName(ENTITY_ORDER.getName())
				.setProperties(properties)
				.setKey(key)
				.setNavigationProperties(navigationProperties);
	}


	public EntityType getCustomerEntityType() {
		List<Property> properties = new ArrayList<Property>();
		properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
		properties.add(new SimpleProperty().setName("FirstName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setMaxLength(40)));
		properties.add(new SimpleProperty().setName("LastName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setMaxLength(40)));
		properties.add(new SimpleProperty().setName("UserName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setMaxLength(40)));

		//Key
		List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
		keyProperties.add(new PropertyRef().setName("Id"));
		Key key = new Key().setKeys(keyProperties);

		return new EntityType().setName(ENTITY_CUSTOMER.getName())
				.setProperties(properties)
				.setKey(key);
	}


	public ComplexType getComplexType(FullQualifiedName edmFQName) throws ODataException {
		if (NAMESPACE.equals(edmFQName.getNamespace())) {
			if (COMPLEX_ADDRESS_TYPE.getName().equals(edmFQName.getName())) {
				List<Property> properties = new ArrayList<Property>();
				properties.add(new SimpleProperty().setName("Country").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("County").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("City").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("StreetAddress").setType(EdmSimpleTypeKind.String));
				return new ComplexType().setName(COMPLEX_ADDRESS_TYPE.getName()).setProperties(properties);
			}
		}
		return null;
	}

	@Override
	public Association getAssociation(FullQualifiedName edmFQName) throws ODataException {
		if (NAMESPACE.equals(edmFQName.getNamespace())) {
			if (ASSOCIATION_ORDER_CUSTOMER.getName().equals(edmFQName.getName())) {
				return new Association().setName(ASSOCIATION_ORDER_CUSTOMER.getName())
							.setEnd1(new AssociationEnd().setType(ENTITY_ORDER).setRole(ROLE_ORDER_CUSTOMER).setMultiplicity(EdmMultiplicity.MANY))
							.setEnd2(new AssociationEnd().setType(ENTITY_CUSTOMER).setRole(ROLE_CUSTOMER_ORDERS).setMultiplicity(EdmMultiplicity.ONE));
			}
			if (ASSOCIATION_ORDERDETAIL_ORDER.getName().equals(edmFQName.getName())) {
				return new Association().setName(ASSOCIATION_ORDERDETAIL_ORDER.getName())
						.setEnd1(new AssociationEnd().setType(ENTITY_ORDERDETAIL).setRole(ROLE_ORDERDETAIL_ORDER).setMultiplicity(EdmMultiplicity.MANY))
						.setEnd2(new AssociationEnd().setType(ENTITY_ORDER).setRole(ROLE_ORDER_ORDERDETAILS).setMultiplicity(EdmMultiplicity.ONE));
			}
			if (ASSOCIATION_ORDERDETAIL_PRODUCT.getName().equals(edmFQName.getName())) {
				return new Association().setName(ASSOCIATION_ORDERDETAIL_PRODUCT.getName())
						.setEnd1(new AssociationEnd().setType(ENTITY_ORDERDETAIL).setRole(ROLE_ORDERDETAIL_PRODUCT).setMultiplicity(EdmMultiplicity.ONE))
						.setEnd2(new AssociationEnd().setType(ENTITY_PRODUCT).setRole(ROLE_PRODUCTS_ORDERDETAIL).setMultiplicity(EdmMultiplicity.MANY));
			}

		}
		return null;
	}

	@Override
	public EntityContainerInfo getEntityContainerInfo(String name) throws ODataException {
		if (name == null || "ODataOrdersEntityContainer".equals(name)) {
			return new EntityContainerInfo().setName("ODataOrdersEntityContainer").setDefaultEntityContainer(true);
		}
		return null;
	}

	@Override
	public EntitySet getEntitySet(String entityContainer, String name) throws ODataException {
		if (ENTITY_CONTAINER.equals(entityContainer)) {
			if (ENTITY_SET_NAME_CUSTOMERS.equals(name)) {
				return new EntitySet().setName(name).setEntityType(ENTITY_CUSTOMER);
			} else if (ENTITY_SET_NAME_PRODUCTS.equals(name)) {
				return new EntitySet().setName(name).setEntityType(ENTITY_PRODUCT);
			} else if (ENTITY_SET_NAME_ORDERS.equals(name)) {
				return new EntitySet().setName(name).setEntityType(ENTITY_ORDER);
			} else if (ENTITY_SET_NAME_ORDERDETIALS.equals(name)) {
				return new EntitySet().setName(name).setEntityType(ENTITY_ORDERDETAIL);
			}
		}
		return null;
	}
}
