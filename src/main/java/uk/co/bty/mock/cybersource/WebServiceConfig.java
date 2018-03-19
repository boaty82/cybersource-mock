package uk.co.bty.mock.cybersource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import uk.co.bty.mock.cybersource.constants.ApplicationConstants;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter
{
	@Resource
	private Wss4jSecurityInterceptor apiSecurityInterceptor;

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(final ApplicationContext applicationContext)
	{
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, ApplicationConstants.LOCATION_URI + "/*");
	}

	@Bean(name = "transaction-data")
	public DefaultWsdl11Definition transactionWsdl(final XsdSchema transactionSchema)
	{
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("TransactionsPort");
		wsdl11Definition.setLocationUri(ApplicationConstants.LOCATION_URI);
		wsdl11Definition.setTargetNamespace(ApplicationConstants.NAMESPACE);
		wsdl11Definition.setSchema(transactionSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema transactionSchema()
	{
		return new SimpleXsdSchema(new ClassPathResource("xjc/CyberSourceTransaction_1.120.xsd"));
	}

	@Bean
	public PayloadLoggingInterceptor payloadLoggingInterceptor()
	{
		return new PayloadLoggingInterceptor();
	}

	@Override
	public void addInterceptors(final List<EndpointInterceptor> interceptors)
	{
		interceptors.add(payloadLoggingInterceptor());
		interceptors.add(apiSecurityInterceptor);
	}

}
