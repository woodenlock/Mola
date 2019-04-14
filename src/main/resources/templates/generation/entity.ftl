package ${all.entity.targetPackagePath?replace('/','.') ? substring(0,(all.entity.targetPackagePath?replace('/','.'))?length-1)};

import java.io.Serializable;
<#list table.imports?values as importance>
import ${importance};
<#if !importance_has_next>

</#if>
</#list>
/**
 * @Description entity class:${table.remarks}
 * @Author: ${author}
 * @Date: ${date}
 **/
public class ${table.entityName? cap_first}${all.entity.targetSuffix} implements Serializable {
	private static final long serialVersionUID = 1L;
	
    <#list table.columns as column>
    /**
	 * ${column.remarks}
	 *
     * ${r"column"}: ${table.name}.${column.name}
     */
    private ${column.javaTypeShortName} ${column.javaName};
    
    </#list>
    <#list table.columns as column>
    /**
     * return ${column.remarks}
     */
    public ${column.javaTypeShortName} get${column.javaName? cap_first}(){
    	return ${column.javaName};
    };
    
    /**
     * set ${column.remarks}
     */
    public void set${column.javaName? cap_first}(${column.javaTypeShortName} ${column.javaName}){
    	this.${column.javaName} = ${column.javaName};
    };
    
    </#list>
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("${table.entityName? cap_first}${all.entity.targetSuffix} [");
        <#list table.columns as column>
        sb.append("${column.javaName}=" + ${column.javaName}<#if column_has_next> + ","</#if>);
        </#list>
        sb.append("]");
        return sb.toString();
    };
}