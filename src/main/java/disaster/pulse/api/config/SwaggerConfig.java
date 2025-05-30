package disaster.pulse.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public GroupedOpenApi civilGroup() {
        return GroupedOpenApi.builder()
                .group("Civil")
                .displayName("Civil")
                .pathsToMatch("/api/login", "/api/cadastro/civil/**", "/api/sos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi entidadeGroup() {
        return GroupedOpenApi.builder()
                .group("Entidade")
                .pathsToMatch("/api/login", "/api/cadastro/entidade/**", "/api/eventos/**", "/api/alertas/**", "/api/tipo-evento/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Disaster Pulse API")
                        .version("v1")
                        .description("API para gerenciamento de eventos de desastres naturais")
                        .contact(new Contact()
                                .name("Suporte API")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
