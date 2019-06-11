package com.resintec.mola.business.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.resintec.mola.model.BusinessException;
import com.resintec.mola.util.CommonUtils;
import com.resintec.mola.util.LogUtils;

/**
 * util to deal with common object
 * @author woodenlock
 *
 */
public class ObjectUtils {
    private static LogUtils.Log log = LogUtils.build(ObjectUtils.class);
    
	/** path of Object class **/
	public static final String OBJECT_PATH = "java.lang.object";
	
	/**
	 * 
	 * serialize object into bytes
	 * @param obj
	 * @return byte[]
	 */
	public static byte[] serialize(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream byteOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(byteOut);
			oos.writeObject(obj);
			byte[] bytes = byteOut.toByteArray();
			return bytes;
		} catch (Exception e) {
		    throw new BusinessException("Failed to serialize object due to fail to write into the stream path.Error:" + e);
		}
	}

	/**
	 * unserialize object from bytes
	 * @param bytes
	 * @return obj
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream in = null;
		try {
			in = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(in);
			return ois.readObject();
		} catch (Exception e) {
		    throw new BusinessException("Failed to unserialize bytes to object due to fail to write datas into the object.Error:" + e);
		}
	}
	
	/**
	 * get a deep copy from target object
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object getDeepClone(Object object) throws IOException, ClassNotFoundException{
		if(null == object || !(object instanceof Serializable)){
		    throw new BusinessException("Failed to get a deep copy due to can't find serializable param.");
		}
		
		ByteArrayOutputStream baos =new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
		
        return ois.readObject();
	}
	
	/**
	 * get a new instance of the target class(with the default non-param constructor)
	 * @param classFullName
	 * @return
	 */
	public static Object newInstance(String classFullName){
	    if(CommonUtils.isBlank(classFullName)){
	        return null;
	    }
	    Object result = null;
	    try {
            Class<?> clazz = Class.forName(classFullName);
            result = clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new BusinessException("Failed to get the new instance of the class[" + classFullName + "] due to error:" + e);
        }
	    return result;
	}
	
	/**
	 * convert object to another object
	 * @param <A>
	 * @param list source object list
	 * @param clazz target object class
	 * @return <T>T
	 */
	public static <T, A> List<T> convertList(List<A> list, Class<T> clazz) {
	    if(null == list || list.isEmpty() || null == clazz){
	        throw new BusinessException("Failed to convert list due to empty param.");
	    }
	    List<T>result = new ArrayList<T>(list.size());
	    for (A object : list) {
	        result.add(convert(object, clazz));
        }
	    
	    return result;
	}
	
	/**
	 * convert object to another object
	 * @param object source object
	 * @param clazz target object class
	 * @return <T>T
	 */
	public static <T>T convert(Object object, Class<T> clazz) {
	    if(null == object || null == clazz){
	        return null;
	    }
	    Map<String, Object> map = convertBean(object);
	    
	    return convertMap(clazz, map);
	}
	
	/**
     * converte a bean into a Map
     * @param bean the target bean
     * @return traget Map
     */
    public static Map<String, Object> convertBean(Object bean) {
        if(null == bean){
            return null;
        }
        Class<?> type = bean.getClass();
        Map<String, Object> result = new HashMap<String, Object>(16);
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            throw new BusinessException("Failed to convert object to map due to fail to get info of the bean.Error:" + e);
        }
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        try {
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object value = readMethod.invoke(bean, new Object[0]);
                    result.put(propertyName, value);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new BusinessException("Failed to convert object to map due to fail to assign value to the target Map.Error:" + e);
        }
        
        return result;
    }
    
    /**
     * convert a Map into a JavaBean
     * @param clazz target class type
     * @param map source Map
     * @return target object
     */
    public static <T>T convertMap(Class<T> clazz, Map<String, Object> map) {
        if(null == clazz || null == map){
            return null;
        }
        T result = null;
        //get props from object
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException | IntrospectionException e) {
            throw new BusinessException("Failed to convert map to object due to error:" + e);
        }
        //start assignment
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        Object object = null;
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                object = map.get(propertyName);
                try {
                    descriptor.getWriteMethod().invoke(result, object);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    log.warn("Exception occured when converting map to object due to fail to assign prop:{}.Error:", propertyName, e);
                }
            }
        }
        
        return result;
    }
}