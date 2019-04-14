package ${all.controller.targetPackagePath?replace('/','.') ? substring(0,(all.controller.targetPackagePath?replace('/','.'))?length-1)};

import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.viewObject.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.viewObject.targetSuffix};
import ${all.service.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.service.targetSuffix};
import com.github.pagehelper.Page;
import com.resintec.mola.business.consts.DBConsts;
import com.resintec.mola.business.model.PageVO;
import com.resintec.mola.business.model.ResponseVO;
import com.resintec.mola.business.util.ObjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description the controller of ${table.entityName}
 * @Author: ${author}
 * @Date: ${date}
 *
 */
@Api(value = "/${table.entityName}", tags = "/${table.remarks}")
@RestController
@RequestMapping("/${table.entityName}")
public class ${table.entityName? cap_first}${all.controller.targetSuffix} {
	private static final Logger log = LoggerFactory.getLogger(${table.entityName? cap_first}${all.controller.targetSuffix}.class);
	
    @Autowired
    private ${table.entityName? cap_first}${all.service.targetSuffix} service;
    
    /**
     * get single record by primary key
     * @return
     */
    @ApiOperation(value = "get single record by primary key", notes = "get single record by primary key")
    @GetMapping(value = "/query/{${table.primaryColumn.name}}")
    public ResponseVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> query(@PathVariable ${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}) {
        ${table.entityName? cap_first}${all.entity.targetSuffix} ${table.entityName} = service.selectByPrimaryKey(${table.primaryColumn.name});
        return new ResponseVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}>(ObjectUtils.convert(${table.entityName}, ${table.entityName? cap_first}${all.viewObject.targetSuffix}.class));
    }
    
    /**
     * query page
     * @param page
     * @return 
     */
    @ApiOperation(value = "query page by selective props", notes = "query page by selective props")
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/queryPage")
    public ResponseVO<PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}>> page(@RequestBody PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page) {
        PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> result = null;
        if(null == page){
            return new ResponseVO<PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}>>(result);
        }
        Page<${table.entityName? cap_first}${all.entity.targetSuffix}> data = service.page(page);
        result = ObjectUtils.convert(data, PageVO.class);
        result.setList(ObjectUtils.convertList(data.getResult(), ${table.entityName? cap_first}${all.viewObject.targetSuffix}.class));
        return new ResponseVO<PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}>>(result);
    }
    
    /**
     * add record
     * @param vo
     * @return
     */
    @ApiOperation(value = "add record", notes = "add record")
    @PostMapping(value = "/create")
    public ResponseVO<Boolean> create(@RequestBody ${table.entityName? cap_first}${all.viewObject.targetSuffix} vo){
    	log.debug("Ready to add ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.", vo);
        ${table.entityName? cap_first}${all.entity.targetSuffix} target = ObjectUtils.convert(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
        if (null == target) {
            return new ResponseVO<Boolean>(Boolean.FALSE);
        }
        int result = service.insertSelective(target);
        log.debug("Finish to add ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.Result:{}.", vo, result);
        return new ResponseVO<Boolean>(result == DBConsts.COMMON_SUCCESS);
    }
    
    /**
     * update record
     * @param vo
     * @return
     */
    @ApiOperation(value = "update record by target id", notes = "update record by target id")
    @PutMapping(value = "/update")
    public ResponseVO<Boolean> update(@RequestBody ${table.entityName? cap_first}${all.viewObject.targetSuffix} vo){
    	log.debug("Ready to update ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.", vo);
        ${table.entityName? cap_first}${all.entity.targetSuffix} target = ObjectUtils.convert(vo, ${table.entityName? cap_first}${all.entity.targetSuffix}.class);
        if (null == target) {
            return new ResponseVO<Boolean>(Boolean.FALSE);
        }
        int result = service.updateByPrimaryKeySelective(target);
        log.debug("Finish to update ${table.entityName? cap_first}${all.viewObject.targetSuffix}:{}.Result:{}.", vo, result);
        return new ResponseVO<Boolean>(result == DBConsts.COMMON_SUCCESS);
    }
    
    /**
     * delete record
     * @param ${table.primaryColumn.name}
     * @return
     */
    @ApiOperation(value = "delete record by target id", notes = "delete record by target id")
    @DeleteMapping(value = "/delete/{${table.primaryColumn.name}}")
    public ResponseVO<Boolean> delete(@PathVariable @ApiParam(name = "${table.primaryColumn.name}", value = "the primary key of the target record wanted to be deleted", required = true) ${table.primaryColumn.javaTypeShortName} ${table.primaryColumn.name}){
    	log.debug("Ready to delete ${table.primaryColumn.javaTypeShortName}:{}.", ${table.primaryColumn.name});
        int result = service.deleteByPrimaryKey(${table.primaryColumn.name});
        log.debug("Finish to delete ${table.primaryColumn.javaTypeShortName}:{}.Result:{}.", ${table.primaryColumn.name}, result);
        return new ResponseVO<Boolean>(result == DBConsts.COMMON_SUCCESS);
    }
}