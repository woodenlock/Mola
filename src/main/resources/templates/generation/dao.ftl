package ${all.dao.targetPackagePath?replace('/','.') ? substring(0,(all.dao.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
 
import java.util.List;

import org.apache.ibatis.annotations.Param;
 
/**
* dao of the ${table.entityName? cap_first}
* @author: ${author}
* @create: ${date}
**/
public interface ${table.entityName? cap_first}${all.dao.targetSuffix} {
	/**
     * delete by the primary key of the target entity
     * @param ${table.primaryColumn.name}
     */
    int deleteByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * insert with all props of the target entity
     * @param record
     */
    int insert(${table.entityName? cap_first} record);
    
    /**
     * insert with selective props of the target entity
     * @param record
     */
    int insertSelective(${table.entityName? cap_first} record);
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     */
    ${table.entityName? cap_first} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * update selective props of the record by primary key
     * @param record
     */
    int updateByPrimaryKeySelective(${table.entityName? cap_first} record);
    
    /**
     * update all props of the record by primary key
     * @param record
     */
    int updateByPrimaryKey(${table.entityName? cap_first} record);
    
    /**
     * select all matched records by selective props
     * @param record
     */
    List<${table.entityName? cap_first}> selectSelective(${table.entityName? cap_first} record);
    
    /**
     * select all matched records by the list of primary keys
     * @param record
     */
    List<${table.entityName? cap_first}> selectByPrimarys(@Param("${table.primaryColumn.name}s")List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s);
}