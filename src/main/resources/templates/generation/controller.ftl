package ${all.controller.targetPackagePath?replace('/','.') ? substring(0,(all.controller.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.viewObject.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.viewObject.targetSuffix};
import ${all.service.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.service.targetSuffix};

import com.resintec.core.utils.DozerUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * the controller of ${table.entityName}
 * @author: ${author}
 * @create: ${date}
 *
 */
@Api(value = "/${table.entityName}", tags = "/${table.remarks}")
@RestController
@RequestMapping("/${table.entityName}")
public class ${table.entityName? cap_first}${all.controller.targetSuffix} {
    @Autowired
    private ${table.entityName? cap_first}${all.service.targetSuffix} service;
    
    /**
     * get single record by primary key
     * @return
     */
    @ApiOperation(value = "get single record by primary key", notes = "get single record by primary key")
    @GetMapping(value = "/query/{${table.primaryColumn.name}}")
    public ${table.entityName? cap_first}${all.viewObject.targetSuffix} query(@PathVariable ${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
        ${table.entityName? cap_first}${all.entity.targetSuffix} ${table.entityName} = service.selectByPrimaryKey(${table.primaryColumn.name});
        return DozerUtil.conversion(${table.entityName}, ${table.entityName? cap_first}${all.viewObject.targetSuffix}.class);
    }
    
    /**
     * query page
     * @param page
     * @return 
     */
    @ApiOperation(value = "query page by selective props", notes = "query page by selective props")
    @PostMapping(value = "/queryPage")
    public List<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page(@RequestBody ${table.entityName? cap_first}${all.viewObject.targetSuffix} target) {
        ${table.entityName? cap_first}${all.entity.targetSuffix} record = DozerUtil.conversion(target, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
        List<${table.entityName? cap_first}${all.entity.targetSuffix}> records = service.selectSelective(record);
        return DozerUtil.conversionList(records, ${table.entityName? cap_first}${all.viewObject.targetSuffix}.class);
    }
    
    /**
     * add record
     * @param vo
     * @return
     */
    @ApiOperation(value = "add record", notes = "add record")
    @PostMapping(value = "/create")
    public Boolean create(@RequestBody ${table.entityName? cap_first}${all.viewObject.targetSuffix} vo){
        ${table.entityName? cap_first}${all.entity.targetSuffix} target = DozerUtil.conversion(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
        if (null == target) {
            return Boolean.FALSE;
        }
        int result = service.insertSelective(target);
        
        return result == 1;
    }
    
    /**
     * update record
     * @param vo
     * @return
     */
    @ApiOperation(value = "update record by target id", notes = "update record by target id")
    @PutMapping(value = "/update")
    public Boolean update(@RequestBody ${table.entityName? cap_first}${all.viewObject.targetSuffix} vo){
        ${table.entityName? cap_first}${all.entity.targetSuffix} target = DozerUtil.conversion(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
        if (null == target) {
            return Boolean.FALSE;
        }
        int result = service.updateByPrimaryKeySelective(target);
        
        return result == 1;
    }
    
    /**
     * delete record
     * @param ${table.primaryColumn.name}
     * @return
     */
    @ApiOperation(value = "delete record by target id", notes = "delete record by target id")
    @DeleteMapping(value = "/delete/{${table.primaryColumn.name}}")
    public Boolean delete(@PathVariable @ApiParam(name = "${table.primaryColumn.name}", value = "the primary key of the target record wanted to be deleted", required = true) ${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}){
        int result = service.deleteByPrimaryKey(${table.primaryColumn.name});

        return result == 1;
    }
}