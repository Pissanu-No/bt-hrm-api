package com.bakertilly.bt_hrm_api.core.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class OpenApiConfiguration {
    private static final String BEARER_AUTH_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI hrisOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HRIS + HRD Platform API")
                        .description("REST API documentation for Enterprise HRIS + HRD Platform")
                        .version("v1"))
                .components(new Components()
                        .addSecuritySchemes(BEARER_AUTH_SCHEME, new SecurityScheme()
                        .name(BEARER_AUTH_SCHEME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi coreMasterDataApi(OperationCustomizer operationDocumentationCustomizer,
                                            OpenApiCustomizer securedEndpointsCustomizer) {
        return GroupedOpenApi.builder()
                .group("Core Master Data")
                .packagesToScan(
                        "com.bakertilly.bt_hrm_api.app.company.controller",
                        "com.bakertilly.bt_hrm_api.app.branch.controller",
                        "com.bakertilly.bt_hrm_api.app.location.controller",
                        "com.bakertilly.bt_hrm_api.app.department.controller",
                        "com.bakertilly.bt_hrm_api.app.position.controller",
                        "com.bakertilly.bt_hrm_api.app.joblevel.controller",
                        "com.bakertilly.bt_hrm_api.app.costcenter.controller",
                        "com.bakertilly.bt_hrm_api.app.master.lookup.controller")
                .addOperationCustomizer(operationDocumentationCustomizer)
                .addOpenApiCustomizer(securedEndpointsCustomizer)
                .build();
    }

    @Bean
    public GroupedOpenApi systemApi(OperationCustomizer operationDocumentationCustomizer,
                                    OpenApiCustomizer securedEndpointsCustomizer) {
        return groupedOpenApi("System", "com.bakertilly.bt_hrm_api.app.system",
                operationDocumentationCustomizer, securedEndpointsCustomizer);
    }

    @Bean
    public GroupedOpenApi employeeManagementApi(OperationCustomizer operationDocumentationCustomizer,
                                                OpenApiCustomizer securedEndpointsCustomizer) {
        return groupedOpenApi("Employee Management", "com.bakertilly.bt_hrm_api.app.employee",
                operationDocumentationCustomizer, securedEndpointsCustomizer);
    }

    @Bean
    public GroupedOpenApi securityApi(OperationCustomizer operationDocumentationCustomizer,
                                      OpenApiCustomizer securedEndpointsCustomizer) {
        return groupedOpenApi("Security", "com.bakertilly.bt_hrm_api.app.security",
                operationDocumentationCustomizer, securedEndpointsCustomizer);
    }

    @Bean
    public GroupedOpenApi auditLogApi(OperationCustomizer operationDocumentationCustomizer,
                                      OpenApiCustomizer securedEndpointsCustomizer) {
        return groupedOpenApi("Audit Log", "com.bakertilly.bt_hrm_api.app.audit",
                operationDocumentationCustomizer, securedEndpointsCustomizer);
    }

    @Bean
    public OperationCustomizer operationDocumentationCustomizer() {
        return (operation, handlerMethod) -> {
            if (operation.getSummary() == null || operation.getSummary().isBlank()) {
                operation.setSummary(toReadableLabel(handlerMethod.getMethod().getName()));
            }

            if (operation.getTags() == null || operation.getTags().isEmpty()) {
                List<String> tags = new ArrayList<>();
                tags.add(resolveControllerTag(handlerMethod));
                operation.setTags(tags);
            }

            return operation;
        };
    }

    @Bean
    public OpenApiCustomizer securedEndpointsCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null || openApi.getPaths().isEmpty()) {
                return;
            }

            Map<String, Tag> discoveredTags = new TreeMap<>();

            openApi.getPaths().forEach((path, pathItem) -> pathItem.readOperations().forEach(operation -> {
                collectTags(discoveredTags, operation);

                if (path.contains("/v1/s/")) {
                    addSecurity(operation, BEARER_AUTH_SCHEME);
                }
            }));

            if (!discoveredTags.isEmpty()) {
                openApi.setTags(new ArrayList<>(discoveredTags.values()));
            }
        };
    }

    private static void collectTags(Map<String, Tag> discoveredTags, Operation operation) {
        if (operation.getTags() == null) {
            return;
        }

        operation.getTags().forEach(tagName -> discoveredTags.putIfAbsent(tagName,
                new Tag().name(tagName).description(tagName + " APIs")));
    }

    private static void addSecurity(Operation operation, String schemeName) {
        List<SecurityRequirement> securityRequirements = operation.getSecurity();
        if (securityRequirements == null) {
            securityRequirements = new ArrayList<>();
            operation.setSecurity(securityRequirements);
        }

        if (securityRequirements.stream().anyMatch(requirement -> requirement.containsKey(schemeName))) {
            return;
        }

        securityRequirements.add(new SecurityRequirement().addList(schemeName));
    }

    private static String resolveControllerTag(HandlerMethod handlerMethod) {
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        if (controllerName.endsWith("Controller")) {
            controllerName = controllerName.substring(0, controllerName.length() - "Controller".length());
        }

        return toReadableLabel(controllerName);
    }

    private static String toReadableLabel(String rawValue) {
        String readableValue = rawValue
                .replaceAll("([a-z0-9])([A-Z])", "$1 $2")
                .replaceAll("_", " ")
                .trim();

        if (readableValue.isEmpty()) {
            return rawValue;
        }

        return Character.toUpperCase(readableValue.charAt(0)) + readableValue.substring(1);
    }

    private static GroupedOpenApi groupedOpenApi(String groupName, String packageToScan,
                                                 OperationCustomizer operationDocumentationCustomizer,
                                                 OpenApiCustomizer securedEndpointsCustomizer) {
        return GroupedOpenApi.builder()
                .group(groupName)
                .packagesToScan(packageToScan)
                .addOperationCustomizer(operationDocumentationCustomizer)
                .addOpenApiCustomizer(securedEndpointsCustomizer)
                .build();
    }
}
