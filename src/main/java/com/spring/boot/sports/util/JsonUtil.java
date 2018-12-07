package com.spring.boot.sports.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author yuderen
 * version 2018/11/27 15:31
 */
public class JsonUtil {
    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public JsonUtil() {
    }

    public static String toString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            LOG.error("json 格式转换错误！", var2);
        }
        return null;
    }

    public static String toPrettyString(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            LOG.error("json 格式转换错误！", var2);
        }
        return null;
    }

    public static Map<String, Object> toMap(String jsonStr) {
        if (jsonStr != null && jsonStr.trim().length() != 0) {
            try {
                return (Map)mapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
            } catch (IOException var2) {
                LOG.error("json 格式转换错误！", var2);
            }
        }
        return null;
    }

    public static List<Map<String, Object>> toListMap(String jsonStr) {
        if (jsonStr != null && jsonStr.trim().length() != 0) {
            try {
                return (List)mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>() {});
            } catch (IOException var2) {
                LOG.error("json 格式转换错误！", var2);
            }
        }
        return null;
    }

    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        if (jsonStr != null && jsonStr.trim().length() != 0) {
            try {
                return mapper.readValue(jsonStr, clazz);
            } catch (IOException var3) {
                LOG.error("json 格式转换错误！", var3);
            }
        }
        return null;
    }

    public static <T> List<T> toListObject(String jsonStr, Class<?> clazz) {
        if (jsonStr != null && jsonStr.trim().length() != 0) {
            try {
                return (List)mapper.readValue(jsonStr, mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, new Class[]{clazz}));
            } catch (Exception var3) {
                LOG.error("json 格式转换错误！", var3);
            }
        }
        return null;
    }

    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.getSerializationConfig().with(df);
        mapper.getDeserializationConfig().with(df);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
