package com.spring.boot.sports.annotation;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuderen
 * @version 2018/8/25 15:30
 */
public class ValidationExecutor {

    public static void validate(Object object){
        if (null != object){
            List<Field> fieldList = new ArrayList();
            Class<?> clazz = object.getClass();
            while (null != clazz){
                fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }
            for (Field field : fieldList){
                field.setAccessible(true);
                validate(field, object);
                field.setAccessible(false);
            }
        }
    }

    public static void notNullValidate(String[] fields, Object object){
        if (null != object){
            for (String fieldName : fields){
                Field field = null;
                long count = 0;
                Class<?> clazz = object.getClass();
                List<Field> fieldList = new ArrayList();
                while (count == 0 && null != clazz){
                    fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
                    count = fieldList.stream().filter((e) -> e.getName().equals(fieldName)).count();
                    if (count == 0){
                        clazz = clazz.getSuperclass();
                        continue;
                    }
                    try {
                        field = clazz.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        throw new ValidationException(fieldName, "目标对象无"+ fieldName + "属性");
                    }
                    field.setAccessible(true);
                    notNullValidate(field, object);
                    field.setAccessible(false);
                }
            }
        }
    }

    private static void notNullValidate(Field field, Object object){
        FieldInfo fieldInfo = getFieldInfo(field, object);
        if (null == fieldInfo.getValidation()){
            return;
        }

        if (null == fieldInfo.getFileValue()){
            throw new ValidationException(fieldInfo.getFileName(), fieldInfo.getFileName() + "不能为空！");
        }
        if (StringUtils.isEmpty(fieldInfo.getFileValue().toString())){
            throw new ValidationException(fieldInfo.getFileName(), fieldInfo.getFileName() + "不能为空！");
        }
        valueValidate(field, fieldInfo);
    }

    private static FieldInfo getFieldInfo(Field field, Object object){
        FieldInfo fieldInfo = new FieldInfo();
        Validation validation = field.getAnnotation(Validation.class);
        if (null == validation){
            return fieldInfo;
        }

        fieldInfo.setValidation(validation);
        fieldInfo.setFileName(StringUtils.isEmpty(validation.name()) ? field.getName() : validation.name());
        Object value = null;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            throw new ValidationException(fieldInfo.getFileName(), "取值报错");
        }
        fieldInfo.setFileValue(value);
        return fieldInfo;
    }

    private static void validate(Field field, Object object){
        FieldInfo fieldInfo = getFieldInfo(field, object);
        if (null == fieldInfo.getValidation()){
            return;
        }

        nullableValidate(fieldInfo);            // 空值参数验证
        valueValidate(field, fieldInfo);        // 属性值合法性验证
    }

    private static void valueValidate(Field field, FieldInfo fieldInfo){
        stringValidate(fieldInfo, field);       // 字符串值参数验证
        numberValidate(fieldInfo, field);       // 数字值类型参数验证
        regexExpressionValidate(fieldInfo);     // 正则表达式参数验证
    }

    private static void nullableValidate(FieldInfo fieldInfo){
        Object value = fieldInfo.getFileValue();
        String fileName = fieldInfo.getFileName();
        if (fieldInfo.getValidation().nullable()){
            if (null == value || StringUtils.isEmpty(value.toString())){
                return;
            }
        } else {
            if (null == value || StringUtils.isEmpty(value.toString())){
                throw new ValidationException(fileName, fileName + "不能为空！");
            }
        }
    }

    private static void stringValidate(FieldInfo fieldInfo, Field field){
        Validation validation = fieldInfo.getValidation();
        String fileName = fieldInfo.getFileName();
        if (null != fieldInfo.getFileValue() && String.class.equals(field.getType())){
            String strValue = fieldInfo.getFileValue().toString();
            if (strValue.length() > validation.max()){
                throw new ValidationException(fileName, fileName + "长度不能大于" + validation.max());
            }
        }
    }

    private static void numberValidate(FieldInfo fieldInfo, Field field){
        Validation validation = fieldInfo.getValidation();
        String fileName = fieldInfo.getFileName();
        if (null != fieldInfo.getFileValue()){
            String strValue = fieldInfo.getFileValue().toString();
            if (Integer.class.equals(field.getType())){
                Integer numberValue = Integer.parseInt(strValue);
                if (numberValue < validation.min()){
                    throw new ValidationException(fileName, fileName + "值不能小于" + validation.min());
                } else if (numberValue > validation.max()){
                    throw new ValidationException(fileName, fileName + "值不能大于" + validation.max());
                }
            } else if (Double.class.equals(field.getType()) || BigDecimal.class.equals(field.getType())){
                BigDecimal bigDecimal = new BigDecimal(strValue);
                if (bigDecimal.compareTo(new BigDecimal(validation.min())) < 0){
                    throw new ValidationException(fileName, fileName + "值不能小于" + validation.min());
                } else if (bigDecimal.compareTo(new BigDecimal(validation.max())) > 0){
                    throw new ValidationException(fileName, fileName + "值不能大于" + validation.max());
                }
            }
        }
    }

    private static void regexExpressionValidate(FieldInfo fieldInfo){
        Validation validation = fieldInfo.getValidation();
        String fileName = fieldInfo.getFileName();
        if (null == fieldInfo.getFileValue() || RegexType.NONE.equals(validation.regexType())){
            return;
        }
        String regexExpression = validation.regexType().getRegexExpression();
        if (RegexType.OTHER.equals(validation.regexType())){
            regexExpression = validation.regexExpression();
        }
        if (StringUtils.isEmpty(regexExpression)){
            return;
        }
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher result = pattern.matcher(fieldInfo.getFileValue().toString());
        if (result.matches()){
            return;
        }
        throw new ValidationException(fileName, fileName + "值不合法");
    }

    @Data
    private static class FieldInfo{
        private Validation validation;
        private String fileName;
        private Object fileValue;
    }

}
