/*
* Archivo: SwaggerConfig.java
* Fecha: 27/01/2023
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del SANTIAGO ROJAS MANIOS
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito de GRUPO ASD S.A.S. quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
 */
package com.grupoasd.activos.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Bean que instancia la coniguración de Swagger.
 *
 * @author Santiago Rojas Manios
 */
@Configuration
public class SwaggerConfig {

    @Value("${application.contac.name}")
    private String contactName;
    @Value("${application.contac.url}")
    private String contactUrl;
    @Value("${application.contac.email}")
    private String contactEmail;
    @Value("${application.info.api.title}")
    private String infoApiTitle;
    @Value("${application.info.api.descriptio}")
    private String infoApiDescription;
    @Value("${application.info.api.version}")
    private String infoApiVersion;
    @Value("${application.info.api.termsOfServiceUrl}")
    private String infoApiTermOfServiceUrl;
    @Value("${application.info.api.license}")
    private String infoApiLicense;
    @Value("${application.info.api.licenseUrl}")
    private String infoApiLicenseUrl;

    @Autowired
    BuildProperties buildProperties;

    public static Contact DEFAULT_CONTACT;
    public static ApiInfo DEFAULT_API_INFO;

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    /**
     * Bean de clase.
     *
     * @return
     */
    @Bean
    public Docket api() {
        infoApiTitle = buildProperties.getName() + " - " + infoApiTitle;
        infoApiVersion = buildProperties.getVersion();

        DEFAULT_CONTACT = new Contact(contactName, contactUrl, contactEmail) {
        };

        DEFAULT_API_INFO = new ApiInfo(infoApiTitle, infoApiDescription, infoApiVersion, infoApiTermOfServiceUrl,
                DEFAULT_CONTACT, infoApiLicense, infoApiLicenseUrl, new ArrayList<>());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grupoasd.activos.api"))
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }
}
