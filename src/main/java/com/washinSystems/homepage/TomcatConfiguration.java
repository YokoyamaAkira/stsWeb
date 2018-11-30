package com.washinSystems.homepage;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//参考
//組み込みtomcat用のJavaConfigの追加
//https://qiita.com/bakira/items/0e25cc19fedf294dcb9f
//Adding embedded Tomcat AJP support to a Spring Boot application
//https://blog.swdev.ed.ac.uk/2015/06/24/adding-embedded-tomcat-ajp-support-to-a-spring-boot-application/

@Configuration
public class TomcatConfiguration {

	@Value("${tomcat.ajp.port}")
	int ajpPort;

	@Value("${tomcat.ajp.RedirectPort}")
	int ajpRedirectPort;

	@Value("${tomcat.ajp.URIEncoding}")
	String ajpURIEncoding;

	@Value("${tomcat.ajp.Protocol}")
	String ajpProtocol;

	@Value("${tomcat.ajp.maxThreads}")
	int maxThreads;

	@Value("${tomcat.ajp.enabled}")
	boolean tomcatAjpEnabled;

	@Value("${tomcat.ajp.Scheme}")
	String ajpScheme;
	
	//tomcat.ajp.Scheme
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		if (tomcatAjpEnabled) {
			tomcat.addAdditionalTomcatConnectors(ajpConnector());
		}
		return tomcat;
	}

	private Connector ajpConnector() {
		Connector connector = new Connector("org.apache.coyote.ajp.AjpNioProtocol");
		connector.setAttribute("maxThreads", maxThreads);
		connector.setProtocol(ajpProtocol);
		connector.setPort(ajpPort);
		connector.setRedirectPort(ajpRedirectPort);
		connector.setScheme(ajpScheme);
		connector.setURIEncoding(ajpURIEncoding);
		return connector;
	}

}
