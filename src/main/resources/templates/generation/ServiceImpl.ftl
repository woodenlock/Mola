package ${all.serviceImpl.targetPackagePath?replace('/','.') ? substring(0,(all.serviceImpl.targetPackagePath?replace('/','.'))?length-1)};

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.resintec.mola.business.model.PageVO;
import com.resintec.mola.business.util.ObjectUtils;
import com.resintec.mola.business.consts.DBConsts;
import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.viewObject.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.viewObject.targetSuffix};
import ${all.dao.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.dao.targetSuffix};
import ${all.service.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.service.targetSuffix};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
 * @Description the service implement of ${table.entityName? cap_first}
 * @Author: ${author}
 * @Date: ${date}
 *
 */
@Service("${table.entityName}${all.service.targetSuffix}")
public class ${table.entityName? cap_first}${all.serviceImpl.targetSuffix} implements ${table.entityName? cap_first}${all.service.targetSuffix} {
	private final static Logger log = LoggerFactory.getLogger(${table.entityName? cap_first}${all.serviceImpl.targetSuffix}.class);
	
	@Autowired
    private ${table.entityName? cap_first}${all.dao.targetSuffix} mapper;
    
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     */
    @Override
    public int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
    	if(null == ${table.primaryColumn.name}) {
    		log.info("Failed to delete record due to empty primary key:${table.primaryColumn.name}.");
    		return DBConsts.COMMON_FAILED;
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
    		log.info("Failed to insert record due to empty param.");
    		return DBConsts.COMMON_FAILED;
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
    		log.info("Failed to insertSelective record due to empty param.");
    		return DBConsts.COMMON_FAILED;
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
    	if(null == record || null == record.get${table.primaryColumn.name? cap_first}()) {
    		log.info("Failed to updateByPrimaryKeySelective record due to empty primary key.${table.entityName? cap_first}${all.entity.targetSuffix}:{}.", record);
    		return DBConsts.COMMON_FAILED;
    	}
    	return mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * update all props of the record by primary key
     * @param record
     */
    @Override
    public int updateByPrimaryKey(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	if(null == record || null == record.get${table.primaryColumn.name? cap_first}()) {
    		log.info("Failed to updateByPrimaryKey record due to empty primary key.${table.entityName? cap_first}${all.entity.targetSuffix}:{}.", record);
    		return DBConsts.COMMON_FAILED;
    	}
    	return mapper.updateByPrimaryKey(record);
    }
    
    @Override
    public Page<${table.entityName? cap_first}${all.entity.targetSuffix}> page(PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page) {
        if (null == page) {
            return null;
        }
        ${table.entityName? cap_first}${all.entity.targetSuffix} param =
            null != page.getSearch() ? ObjectUtils.convert(page.getSearch(), ${table.entityName? cap_first}${all.entity.targetSuffix}.class) : new ${table.entityName? cap_first}${all.entity.targetSuffix}();
        PageHelper.orderBy(page.getOrders());
        Page<${table.entityName? cap_first}${all.entity.targetSuffix}> p = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        mapper.selectSelective(param);
        return p;
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