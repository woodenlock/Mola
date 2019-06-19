package com.resintec.mola.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resintec.mola.business.util.ObjectUtils;
import com.resintec.mola.model.BusinessException;

/**
 * util to deal with common business works
 * @author woodenlock
 *
 */
public class CommonUtils{
	/** hump regx match String **/
    private static final String HUMP_REGX = "_(\\w)";
    
    /** common time format String **/
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSS";
	
    /**
     * underline to hump
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = Pattern.compile(HUMP_REGX).matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    /**
     * transfor first cahr of the String to upper case
     * @param str
     * @return
     */
    public static String stringSupperFirst(String str) {
    	return isBlank(str) ? str : String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }
    
    /**
     * wether the target String is empty
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * wether the target array is empty
     * @param objects
     * @return
     */
    public static boolean isBlank(Object[] objects) {
    	return null == objects || objects.length == 0;
    }
    
    /**
     * iterator props of the target object
     * @param object
     * @info {} meaning to entity or collection,[] meaning to array
     * @return String
     */
    public static String toString(Object object){
        if(null == object){
            return "null";
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        if(isBasicType(object)){
            return object.toString();
        }else if (object.getClass().isArray()) {
            sb.append(array2String(object));
        } else if (object instanceof Map) {
            Iterator<?> iterator = ((Map<?, ?>) object).entrySet().iterator();
            sb.append(iterator2String(iterator));
        } else if (object instanceof Iterator) {
            sb.append(iterator2String((Iterator<?>) object));
        } else if (object instanceof Enumeration) {
            sb.append("{");
            tmp = new StringBuffer();
            while (((Enumeration<?>) object).hasMoreElements()) {
                tmp.append((String) ((Enumeration<?>) object).nextElement());
            }
            if (tmp.length() > 0 && ',' == tmp.charAt(tmp.length() - 1)) {
                tmp.deleteCharAt(tmp.length() - 1);
            }
            sb.append(tmp);
            sb.append("}");
        } else {
            sb.append("{");
            Field[] sfs = object.getClass().getDeclaredFields();
            for (Field f : sfs) {
                f.setAccessible(true);
                sb.append("\"").append(f.getName()).append("\":");
                Object prop;
                try {
                    prop = f.get(object);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new BusinessException("Failed to convert object to String due to error:" + e);
                }
                sb.append(toString(prop) + ",");
            }
            if(!isBlank(sfs)){
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("}");
        }
        
        return sb.toString();
    }
    
    /**
     * convert iterator into StringBuffer
     * @param it
     * @return
     */
    public static <T> String iterator2String(Iterator<T> it) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String key = null == entry.getKey() ? " null" : entry.getKey().toString();
            String value = null == entry.getValue() ? " null" : entry.getValue().toString();
            sb.append("\"" + key + "\":\"" + value + "\",");
        }
        if (sb.length() > 0 && ',' == sb.charAt(sb.length() - 1)){
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        
        return sb.toString();
    }
    
    /**
     * convert array into StringBuffer
     * @param array
     * @return
     */
    public static String array2String(Object array) {
        if(null == array){
            throw new BusinessException("Failed to convert array to String due to empty param.");
        }
        Class<?> clazz = array.getClass();
        if(!clazz.isArray()){
            throw new BusinessException("Failed to convert array to String due to the target param is not a Array:" + array);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < Array.getLength(array); i++) {
            sb.append(toString(Array.get(array, i)) + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * wether the object is a basic type of java
     * @param o
     * @return
     */
    public static boolean isBasicType(Object o){
		return o instanceof  Byte || o instanceof Character
				|| o instanceof String || o instanceof Short
				|| o instanceof Integer || o instanceof Long
				|| o instanceof Double || o instanceof Float
				|| o instanceof Boolean || o instanceof Void;
	}
    
    /**
     * Get all suitable private properties of ancestors of the target class(Except java.lang.Object).
     * @param target
     * @param type
     * @return
     */
    public static List<Field> getAllFields(Class<? extends Object> target, Class<? extends Object> type){
        List<Field> fields = new ArrayList<Field>();
        Field[] tmp = null;
        while(null != target && !ObjectUtils.OBJECT_PATH.equals(target.getName())){
            tmp = target.getDeclaredFields();
            if(null != tmp){
                for (Field field : tmp) {
                	if(null == type || type.isAssignableFrom(field.getType())) {
                		fields.add(field);
                	}
                }
            }
            target = target.getSuperclass();
        }
        
        return fields;
    }
    
    /**
     * get detail string of the current time
     * @return
     */
    public static String getCurrentTime() {
    	return new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }
}
