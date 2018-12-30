package ${all.service.targetPackagePath?replace('/','.') ? substring(0,(all.service.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};

import java.util.List;
 
/**
 * service interface of ${table.entityName? cap_first}
 * @author: ${author}
 * @create: ${date}
 *
 */
public interface ${table.entityName? cap_first}${all.service.targetSuffix} {
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     */
    int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * insert with all props of the target entity
     * @param record
     */
    int insert(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * insert with selective props of the target entity
     * @param record
     */
    int insertSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     */
    ${table.entityName? cap_first}${all.entity.targetSuffix} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * update selective props of the record by primary key
     * @param record
     */
    int updateByPrimaryKeySelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * update all props of the record by primary key
     * @param record
     */
    int updateByPrimaryKey(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * select all matched records by selective props
     * @param record
     */
    List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectSelective(${table.entityName? cap_first}${all.entity.targetSuffix} record);
    
    /**
     * select all matched records by the list of primary keys
     * @param record
     */
    List<${table.entityName? cap_first}${all.entity.targetSuffix}> selectByPrimarys(List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s);
}