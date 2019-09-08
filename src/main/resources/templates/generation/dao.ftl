package ${all.dao.targetPackagePath?replace('/','.') ? substring(0,(all.dao.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
 
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
 
/**
 * the dao of the ${table.entityName? cap_first}
 * @author ${author}
 * @date ${date}
**/
@Repository
public interface ${table.entityName? cap_first}${all.dao.targetSuffix} {
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
    int insert(${table.entityName? cap_first} record);
    
    /**
     * insert with selective props of the target entity
     * @param record
     * @return 
     */
    int insertSelective(${table.entityName? cap_first} record);
    
    /**
     * select by the primary key
     * @param ${table.primaryColumn.name}
     * @return 
     */
    ${table.entityName? cap_first} selectByPrimaryKey(${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name});
    
    /**
     * update selective props of the record by primary key
     * @param record
     * @return 
     */
    int updateByPrimaryKeySelective(${table.entityName? cap_first} record);
    
    /**
     * update all props of the record by primary key
     * @param record
     * @return 
     */
    int updateByPrimaryKey(${table.entityName? cap_first} record);
    
    /**
     * select all matched records by selective props
     * @param record
     * @return 
     */
    List<${table.entityName? cap_first}> selectSelective(${table.entityName? cap_first} record);
    
    /**
     * select the count of  all matched records by selective props
     * @param record
     * @return 
     */
    Long selectCountSelective(${table.entityName? cap_first} record);
    
    /**
     * select all matched records by the list of primary keys
     * @param ${table.primaryColumn.name}s
     * @return 
     */
    List<${table.entityName? cap_first}> selectByPrimarys(@Param("${table.primaryColumn.name}s")List<${table.primaryColumn.javaTypeShortName}> ${table.primaryColumn.name}s);
}