package ${all.service.targetPackagePath?replace('/','.') ? substring(0,(all.service.targetPackagePath?replace('/','.'))?length-1)};

import com.github.pagehelper.Page;
import com.resintec.mola.business.model.PageVO;
import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.viewObject.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.viewObject.targetSuffix};

import java.util.List;
 
/**
 * the service interface of ${table.entityName? cap_first}
 * @author ${author}
 * @date ${date}
 *
 */
public interface ${table.entityName? cap_first}${all.service.targetSuffix} {
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     * @return 
     */
    int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * insert with all props of the target entity
     * @param record
     * @return 
     */
    int insert(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * insert with selective props of the target entity
     * @param record
     * @return 
     */
    int insertSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     * @return 
     */
    ${table.entityName? cap_first}${all.entity.targetSuffix} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * update selective props of the record by primary key
     * @param record
     * @return 
     */
    int updateByPrimaryKeySelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * update all props of the record by primary key
     * @param record
     * @return 
     */
    int updateByPrimaryKey(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * query by page
     * @param page
     * @return
     */
    Page<${table.entityName? cap_first}${all.entity.targetSuffix}> page(PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page);
    
    /**
     * select all matched records by selective props
     * @param record
     * @return 
     */
    List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * select all matched records by the list of primary keys
     * @param ${table.primaryColumn.name}s
     * @return 
     */
    List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectByPrimarys(List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s);

    /**
     * 业务新增
     * @param vo
     * @return [参数说明]
     */
    int add(${table.entityName? cap_first}${all.viewObject.targetSuffix} vo);

    /**
     * 业务修改
     * @param vo
     * @return [参数说明]
    */
    int update(${table.entityName? cap_first}${all.viewObject.targetSuffix} vo);
}