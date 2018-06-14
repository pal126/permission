package com.pal.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String obj2String(T t) {
        if (t == null) {
            return null;
        }
        try {
            return t instanceof String ? (String)t : objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            log.warn("parse object 2 string exception:", e);
            return null;
        }
    }

    public static <T> T string2Obj(String string, TypeReference<T> tTypeReference) {
        if (string == null || tTypeReference == null) {
            return null;
        }
        try {
            return (T) (tTypeReference.getType().equals(String.class) ? string : objectMapper.readValue(string, tTypeReference));
        } catch (Exception e) {
            log.warn("parse string 2 object exception:", e);
            return null;
        }
    }
}
