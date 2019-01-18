package ro.msg.learning.shop.odata;

import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.ODataCallback;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.apache.olingo.odata2.api.processor.ODataErrorContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.ref.factory.JPAEntityManagerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JPAServiceFactory extends ODataJPAServiceFactory {

	private final LocalContainerEntityManagerFactoryBean factory;

	private static final String PUNIT_NAME = "default";

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		JPAEdmExtension jpaEdmExtension = new ODataJPAEdmExtension();
			this.setDetailErrors(true);
		ODataJPAContext oDataJPAContext = this.getODataJPAContext();
		oDataJPAContext.setEntityManagerFactory((factory.getObject()));
		oDataJPAContext.setPersistenceUnitName("default");
/*
		oDataJPAContext.setJPAEdmMappingModel("jpamappingmodel.xml");
*/
		oDataJPAContext.setJPAEdmExtension(jpaEdmExtension);
		return oDataJPAContext;
	}


	@Override
	public <T extends ODataCallback> T getCallback(Class<T> callbackInterface) {
		if (callbackInterface.isAssignableFrom(ODataErrorCallback.class)) {
			return (T) new MySampleErrorCallback();
		}
		return super.getCallback(callbackInterface);
	}

	private class MySampleErrorCallback implements ODataErrorCallback {
		@Override
		public ODataResponse handleError(ODataErrorContext context) throws ODataApplicationException {
			if (context.getHttpStatus() == HttpStatusCodes.INTERNAL_SERVER_ERROR) {
				context.getException().printStackTrace();
			}
			return EntityProvider.writeErrorDocument(context);
		}
	}
}
