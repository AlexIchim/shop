package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ExampleServlet extends ODataServlet {
	private final transient ApplicationContext context;

	@Autowired
	public ExampleServlet(ApplicationContext context) {
		this.context = context;
	}

	@Override
	protected ODataServiceFactory getServiceFactory(HttpServletRequest request) {
		return context.getBean(JPAServiceFactory.class);
	}
}
