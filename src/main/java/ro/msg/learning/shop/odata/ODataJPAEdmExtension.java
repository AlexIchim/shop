package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmSchemaView;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class ODataJPAEdmExtension implements JPAEdmExtension {
	@Override
	public void extendWithOperation(JPAEdmSchemaView jpaEdmSchemaView) {

	}

	@Override
	public void extendJPAEdmSchema(JPAEdmSchemaView jpaEdmSchemaView) {

	}

	@Override
	public InputStream getJPAEdmMappingModelStream() {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("mappingmodel.xml");
	}
}
