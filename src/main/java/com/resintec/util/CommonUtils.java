package com.resintec.util;

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

/**
 * util to deal with common business works
 * @author woodenlock
 *
 */
public class CommonUtils{
	/** hump regx match String **/
    private static final String HUMP_REGX = "_(\\w)";
    
    /** common time format String **/
	private static final String TIME_FORMAT = "yyyy-mm-dd HH:MM:ss.SSSS";
	
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
     * transfer object to String
     * @param object
     * @return
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public static String detailsToString(Object object) throws IllegalArgumentException, IllegalAccessException{
        if(null == object){
            return "{}";
        }
		if(isBasicType(object)){
		    return object.toString();
		}
		
		if(object.getClass().isArray()){
		    StringBuffer tmp1 = new StringBuffer();
		    Object[] array = (Object[]) object;
		    if(null == array || array.length == 0){
		        return "[]";
		    }
		    tmp1.append("[");
		    for (Object obj : array) {
		        tmp1.append(detailsToString(obj) + ",");
            }
		    return tmp1.deleteCharAt(tmp1.length() - 1).append("]").toString();
		}
		
		StringBuffer sb = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        sb.append("{");
		
		List<Field> sfs = getAllFields(object.getClass(), null);
		for (Field f : sfs) {
            f.setAccessible(true);
            sb.append("\"").append(f.getName()).append("\":");
            Object prop = f.get(object);
            if (null != prop) {
                if (isBasicType(prop)){
                    sb.append("\""+prop.toString()+"\"");
                }else if (prop.getClass().isArray()) {
                    Object[] array = (Object[]) prop;
                    if(null == array || array.length == 0){
                        sb.append("[]");
                    }else{
                        StringBuffer tmp1 = new StringBuffer();
                        tmp1.append("[");
                        for (Object o : array) {
                            tmp1.append(detailsToString(o) + ",");
                        }
                        sb.append(tmp1.deleteCharAt(tmp1.length() - 1).append("]"));
                    }
                } else if (prop instanceof Map) {
                    sb.append(iteratorToStringBuffer(((Map<?, ?>) prop).entrySet().iterator()));
                } else if (prop instanceof Iterator) {
                    sb.append(iteratorToStringBuffer((Iterator<?>) prop));
                } else if (prop instanceof Enumeration) {
                    sb.append("{");
                    tmp = new StringBuffer();
                    while (((Enumeration<?>) prop).hasMoreElements()) {
                        tmp.append((String) ((Enumeration<?>) prop).nextElement());
                    }
                    if (tmp.length() > 0 && ',' == tmp.charAt(tmp.length() - 1)) {
                        tmp.deleteCharAt(tmp.length() - 1);
                    }
                    sb.append(tmp);
                    sb.append("}");
                } else {
                    sb.append("\""+detailsToString(prop)+"\"");
                }
            } else {
                sb.append("\"null\"");
            }
            sb.append(",");
        }

		return sb.deleteCharAt(sb.length() - 1).append("}").toString();
	}
    
    /**
     * transfer iterator to StringBuffer
     * @param it
     * @return
     */
    @SuppressWarnings("rawtypes")
	private static StringBuffer iteratorToStringBuffer(Iterator<?> it) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = null == entry.getKey() ? " null" : entry.getKey().toString();
			String value = null == entry.getValue() ? " null" : entry.getValue().toString();
			sb.append("\"" + key + "\":\"" + value + "\",");
		}
		if (sb.length() > 0 && ',' == sb.charAt(sb.length() - 1)){
		    sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");

		return sb;
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
				|| o instanceof Boolean;
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
        while(null != target && !target.getName().equals("java.lang.Object")){
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
