package ${all.serviceImpl.targetPackagePath?replace('/','.') ? substring(0,(all.serviceImpl.targetPackagePath?replace('/','.'))?length-1)};

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.resintec.core.exception.BaseException;
import com.resintec.core.util.DozerUtil;
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
 * the service implement of ${table.entityName? cap_first}
 * @author ${author}
 * @date ${date}
 *
 */
@Service("${table.entityName}${all.service.targetSuffix}")
public class ${table.entityName? cap_first}${all.serviceImpl.targetSuffix} implements ${table.entityName? cap_first}${all.service.targetSuffix} {
	private final static Logger log = LoggerFactory.getLogger(${table.entityName? cap_first}${all.serviceImpl.targetSuffix}.class);
	
	@Autowired
    private ${table.entityName? cap_first}${all.dao.targetSuffix} ${table.entityName}Mapper;
    
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     */
    @Override
    public int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
    	return null == ${table.primaryColumn.name} ? DBConsts.COMMON_FAILED : ${table.entityName}Mapper.deleteByPrimaryKey(${table.primaryColumn.name});
    }
    
    /**
     * insert with all props of the target entity
     * @param record
     */
    @Override
    public int insert(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	return null == record ? DBConsts.COMMON_FAILED : ${table.entityName}Mapper.insert(record);
    }
    
    /**
     * insert with selective props of the target entity
     * @param record
     */
    @Override
    public int insertSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	return null == record ? DBConsts.COMMON_FAILED : ${table.entityName}Mapper.insertSelective(record);
    }
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     */
    @Override
    public ${table.entityName? cap_first}${all.entity.targetSuffix} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
    	return null == ${table.primaryColumn.name} ?
            null : ${table.entityName}Mapper.selectByPrimaryKey(${table.primaryColumn.name});
    }
    
    /**
     * update selective props of the record by primary key
     * @param record
     */
    @Override
    public int updateByPrimaryKeySelective(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	return null == record || null == record.get${table.primaryColumn.name? cap_first}() ?
            DBConsts.COMMON_FAILED : ${table.entityName}Mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * update all props of the record by primary key
     * @param record
     */
    @Override
    public int updateByPrimaryKey(${table.entityName? cap_first}${all.entity.targetSuffix} record) {
    	return null == record || null == record.get${table.primaryColumn.name? cap_first}() ?
            DBConsts.COMMON_FAILED : ${table.entityName}Mapper.updateByPrimaryKey(record);
    }
    
    @Override
    public Page<${table.entityName? cap_first}${all.entity.targetSuffix}> page(PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page) {
        Page<${table.entityName? cap_first}${all.entity.targetSuffix}> result = null;
        if (null != page) {
            ${table.entityName? cap_first}${all.entity.targetSuffix} param =
                null != page.getSearch() ? ObjectUtils.convert(page.getSearch(), ${table.entityName? cap_first}${all.entity.targetSuffix}.class) : new ${table.entityName? cap_first}${all.entity.targetSuffix}();
            PageHelper.orderBy(page.getOrders());
            result = PageHelper.startPage(page.getPageNum(), page.getPageSize());
            selectSelective(param);
        }

        return result;
    }
    
    /**
     * select all matched records by selective props
     * @param record
     */
    @Override
    public List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectSelective(${table.entityName? cap_first} record) {
    	record = null == record ? new ${table.entityName? cap_first}() : record;
    	return ${table.entityName}Mapper.selectSelective(record);
    }
    
    /**
     * select all matched records by the list of primary keys
     * @param ${table.primaryColumn.name}s
     */
    @Override
    public List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectByPrimarys(List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s) {
    	return null == ${table.primaryColumn.name}s ? null : ${table.entityName}Mapper.selectByPrimarys(${table.primaryColumn.name}s);
    }

    @Override
    public int add(${table.entityName? cap_first}${all.viewObject.targetSuffix} vo) {
        log.info("Ready to add ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.", vo);
        int result = DBConsts.COMMON_FAILED;
        if(null != vo){
            vo.setId(null);
            ${table.entityName? cap_first}${all.entity.targetSuffix} record = DozerUtil.conversion(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
            //TODO validation and business work
            result = insertSelective(record);
            if(!DBConsts.COMMON_SUCCESS.equals(result)){
                log.info("Failed to add record due to fail to insertSelective record:{}.", record);
                throw new BaseException("Failed to add record due to fail to insertSelective record.");
            }
        }

        return result;
    }

    @Override
    public int update(${table.entityName? cap_first}${all.viewObject.targetSuffix} vo) {
        log.info("Ready to update ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.", vo);
        int result = DBConsts.COMMON_FAILED;
        if(null != vo && null != vo.getId()) {
            ${table.entityName? cap_first}${all.entity.targetSuffix} record = DozerUtil.conversion(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
            //TODO validation and cover
            result = updateByPrimaryKeySelective(record);
            if(!DBConsts.COMMON_SUCCESS.equals(result)){
                log.info("Failed to update record due to fail to updateByPrimaryKeySelective record:{}.", record);
                throw new BaseException("Failed to add record due to fail to updateByPrimaryKeySelective record.");
            }
        }

        return result;
    }

}