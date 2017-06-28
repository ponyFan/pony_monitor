package com.pony.common.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by zelei.fan on 2017/6/22.
 */
public class BeanCopyUtil {

    private static final Pattern GET_PREFIX = Pattern.compile("^get.*");

    private static final Pattern SET_PREFIX = Pattern.compile("^set.*");

    private static final String GET = "get";

    private static final String SET = "set";


    public static void copy(Object source, Object target){

        Class<?> sClazz = source.getClass();
        Class<?> tClazz = target.getClass();

        Field[] fields = sClazz.getDeclaredFields();
        Field[] fields1 = tClazz.getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            String property = field.getName();
            Class<?> type = field.getType();
            if (type.toString().equals("class java.lang.Integer")){
                type = int.class;
            }
            for (Field field1 : fields1){
                field1.setAccessible(true);
                Class<?> tType = field1.getType();
                if (tType.toString().equals("class java.lang.Integer")){
                    tType = int.class;
                }
                if (property.equals(field1.getName()) && type.equals(tType)){
                    String setName = null;
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(SET).append(property.substring(0,1).toUpperCase()).append(property.substring(1));
                    setName = buffer.toString();
                    Method tMethod = null;
                    try {
                        tMethod = tClazz.getMethod(setName, field1.getType());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    /*通过method方法来获取该属性的值*/
                    if (tMethod != null) {
                        try {
                            tMethod.invoke(target, field.get(source));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
