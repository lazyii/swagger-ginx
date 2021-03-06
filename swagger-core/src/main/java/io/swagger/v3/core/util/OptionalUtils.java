package io.swagger.v3.core.util;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import java.util.Arrays;
import org.rainday.logging.Logger;
import org.rainday.logging.LoggerFactory;

public abstract class OptionalUtils {

    private static Logger logger = LoggerFactory.getLogger(OptionalUtils.class);

    public static boolean _isOptionalType(JavaType jtype) {

        return Arrays.asList("com.google.common.base.Optional", "java.util.Optional")
                .contains(jtype.getRawClass().getCanonicalName());
    }

    /**
     * check if type is an Optional type, returns the unwrapped type in case, otherwise null
     *
     * @param type
     *
     */
    public static AnnotatedType unwrapOptional(AnnotatedType type) {

        if (type == null) {
            return type;
        }
        try {
            final JavaType jtype;
            if (type.getType() instanceof JavaType) {
                jtype = (JavaType) type.getType();
            } else {
                jtype = Json.mapper().constructType(type.getType());
            }

            if (_isOptionalType(jtype)) {
                AnnotatedType aType = new AnnotatedType()
                        .type(jtype.containedType(0))
                        .name(type.getName())
                        .parent(type.getParent())
                        .jsonUnwrappedHandler(type.getJsonUnwrappedHandler())
                        .skipOverride(true)
                        .schemaProperty(type.isSchemaProperty())
                        .ctxAnnotations(type.getCtxAnnotations())
                        .resolveAsRef(type.isResolveAsRef())
                        .jsonViewAnnotation(type.getJsonViewAnnotation())
                        .skipSchemaName(type.isSkipSchemaName())
                        .skipJsonIdentity(type.isSkipJsonIdentity())
                        .propertyName(type.getPropertyName());
                return aType;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error unwrapping optional", e);
            return null;
        }
    }
}
