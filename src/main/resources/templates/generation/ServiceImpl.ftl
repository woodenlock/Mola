package ${all.serviceImpl.targetPackagePath?replace('/','.') ? substring(0,(all.serviceImpl.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.dao.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.dao.targetSuffix};
import ${all.service.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.service.targetSuffix};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
 * the service implement of ${table.entityName? cap_first}
 * @author: ${author}
 * @create: ${date}
 *
 */
@Service("${table.entityName}${all.service.targetSuffix}")
public class ${table.entityName? cap_first}${all.serviceImpl.targetSuffix} implements ${table.entityName? cap_first}${all.service.targetSuffix} {
	@Autowired
    private ${table.entityName? cap_first}${all.dao.targetSuffix} mapper;
    
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     */
    @Override
    public int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
    	if(null == ${table.primaryColumn.name}) {
    		return 0;
    	}
    	return mapper.deleteByPrimaryKey(${table.primaryColumn.name});
    }
    
    /**
     * insert with all props of the target entity
     * @param record
     */
    @Override
    public int insert(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	if(null == record) {
    		return 0;
    	}
    	return mapper.insert(record);
    }
    
    /**
     * insert with selective props of the target entity
     * @param record
     */
    @Override
    public int insertSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	if(null == record) {
    		return 0;
    	}
    	return mapper.insertSelective(record);
    }
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     */
    @Override
    public ${table.entityName? cap_first}${all.entity.targetSuffix} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
    	if(null == ${table.primaryColumn.name}) {
    		return null;
    	}
    	return mapper.selectByPrimaryKey(${table.primaryColumn.name});
    }
    
    /**
     * update selective props of the record by primary key
     * @param record
     */
    @Override
    public int updateByPrimaryKeySelective(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	if(null == record) {
    		return 0;
    	}
    	return mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * update all props of the record by primary key
     * @param record
     */
    @Override
    public int updateByPrimaryKey(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	if(null == record) {
    		return 0;
    	}
    	return mapper.updateByPrimaryKey(record);
    }
    
    /**
     * select all matched records by selective props
     * @param record
     */
    @Override
    public List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectSelective(${table.entityName? cap_first} record) {
    	record = null == record ? new ${table.entityName? cap_first}() : record;
    	return mapper.selectSelective(record);
    }
    
    /**
     * select all matched records by the list of primary keys
     * @param record
     */
    @Override
    public List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectByPrimarys(List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s) {
    	if(null == ${table.primaryColumn.name}s) {
    		return null;
    	}
    	return mapper.selectByPrimarys(${table.primaryColumn.name}s);
    }
}