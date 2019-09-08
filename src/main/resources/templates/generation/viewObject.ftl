package ${all.viewObject.targetPackagePath?replace('/','.') ? substring(0,(all.viewObject.targetPackagePath?replace('/','.'))?length-1)};

import java.io.Serializable;
<#list table.imports?values as importance>
import ${importance};
<#if !importance_has_next>

</#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * the view object class:${table.remarks}
 * @author ${author}
 * @date ${date}
 **/
@ApiModel(value = "${table.remarks}", description = "")
public class ${table.entityName? cap_first}${all.viewObject.targetSuffix} implements Serializable {
	private static final long serialVersionUID = 1L;
	
    <#list table.columns as column>
    /**
	 * ${column.remarks}
	 *
     * ${r"column"}: ${table.name}.${column.name}
     */
    @ApiModelProperty(value = "${column.remarks}")
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
        sb.append("${table.entityName? cap_first}${all.viewObject.targetSuffix} [");
        <#list table.columns as column>
        sb.append("${column.javaName}=" + ${column.javaName}<#if column_has_next> + ","</#if>);
        </#list>
        sb.append("]");
        return sb.toString();
    };
}