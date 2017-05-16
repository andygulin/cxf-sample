package cxf.sample.config;

import cxf.sample.webservice.MessageService;
import cxf.sample.webservice.UserService;
import cxf.sample.webservice.impl.MessageServiceImpl;
import cxf.sample.webservice.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Bean
    public LoggingInInterceptor loggingInInterceptor() {
        return new LoggingInInterceptor();
    }

    @Bean
    public LoggingOutInterceptor loggingOutInterceptor() {
        return new LoggingOutInterceptor();
    }

    @Bean
    public JSONProvider jsonProvider() {
        JSONProvider jsonProvider = new JSONProvider();
        jsonProvider.setSerializeAsArray(true);
        jsonProvider.setIgnoreNamespaces(true);
        jsonProvider.setDropRootElement(true);
        jsonProvider.setDropCollectionWrapperElement(true);
        jsonProvider.setIgnoreMixedContent(true);
        jsonProvider.setSupportUnwrapped(true);
        return jsonProvider;
    }

    @Bean("messageService")
    public MessageService messageService() {
        return new MessageServiceImpl();
    }

    @Bean("userService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Arrays.asList(messageService(), userService()));
        endpoint.setAddress("/");
        endpoint.setInInterceptors(Collections.singletonList(loggingInInterceptor()));
        endpoint.setOutInterceptors(Collections.singletonList(loggingOutInterceptor()));
        Map<Object, Object> extMaps = new HashMap<>(2);
        extMaps.put("json", "application/json");
        extMaps.put("xml", "application/xml");
        endpoint.setExtensionMappings(extMaps);
        Map<Object, Object> lMaps = new HashMap<>(1);
        lMaps.put("en", "en-gb");
        endpoint.setLanguageMappings(lMaps);
        endpoint.setProvider(jsonProvider());
        return endpoint.create();
    }
}
