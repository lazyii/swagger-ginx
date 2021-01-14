/**
 * Copyright 2017 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.v3.oas.annotations.security;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation may be used at class level (also on multiple classes) to add securitySchemes to spec
 * components section.
 *
 * @see <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#securitySchemeObject">Security Scheme (OpenAPI specification)</a>
 * @see <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#componentsObject">Components (OpenAPI specification)</a>
 **/
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SecuritySchemes.class)
@Inherited
public @interface SecurityScheme {
    /**
     * The type of the security scheme. Valid values are "apiKey", "http", "oauth2", "openIdConnect".
     *
     * @return String type
     **/
    SecuritySchemeType type();

    /**
     * The name identifying this security scheme
     *
     * @return String name
     **/
    String name() default "";

    /**
     * A short description for security scheme. CommonMark syntax can be used for rich text representation.
     *
     * @return String description
     **/
    String description() default "";

    /**
     * The name of the header or query parameter to be used. Applies to apiKey type.
     * Maps to "name" property of <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#securitySchemeObject">Security Scheme (OpenAPI specification)</a>
     *
     * @return String paramName
     **/
    String paramName() default "";

    /**
     * The location of the API key. Valid values are "query" or "header".  Applies to apiKey type.
     *
     * @return String in
     **/
    SecuritySchemeIn in() default SecuritySchemeIn.DEFAULT;

    /**
     * The name of the HTTP Authorization scheme to be used in the Authorization header as defined in RFC 7235.  Applies to http type.
     *
     * @return String scheme
     **/
    String scheme() default "";

    /**
     * A hint to the client to identify how the bearer token is formatted. Bearer tokens are usually generated by an
     * authorization server, so this information is primarily for documentation purposes.  Applies to http ("bearer") type.
     *
     * @return String bearerFormat
     **/
    String bearerFormat() default "";

    /**
     * Required. An object containing configuration information for the flow types supported.  Applies to oauth2 type.
     *
     * @return OAuthFlows flows
     **/
    OAuthFlows flows() default @OAuthFlows;

    /**
     * Required. OpenId Connect URL to discover OAuth2 configuration values. This MUST be in the form of a URL.  Applies to openIdConnect.
     *
     * @return String openIdConnectUrl
     **/
    String openIdConnectUrl() default "";

    /**
     * The list of optional extensions
     *
     * @return an optional array of extensions
     */
    Extension[] extensions() default {};

    /**
     * A reference to a SecurityScheme defined in components securitySchemes.
     *
     * @since 2.0.3
     * @return the reference
     **/
    String ref() default "";

}
