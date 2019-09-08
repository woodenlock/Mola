package ${all.testService.targetPackagePath?replace('/','.') ? substring(0,(all.testService.targetPackagePath?replace('/','.'))?length-1)};

import com.github.pagehelper.Page;
import com.resintec.mola.business.model.PageVO;
import ${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix};
import ${all.viewObject.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.viewObject.targetSuffix};
import ${all.service.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.service.targetSuffix};

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * the test class of the ${table.entityName? cap_first}${all.service.targetSuffix}
 * @author ${author}
 * @date ${date}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Throwable.class)
@Rollback
public class ${table.entityName? cap_first}${all.testService.targetSuffix} {
    private static final String PREFIX = "junit-test-";
    
    private static final String REMARK = PREFIX+"aaa";
    
    private ${table.entityName? cap_first}${all.entity.targetSuffix} record = new ${table.entityName? cap_first}${all.entity.targetSuffix}();
    
    @Autowired
    private ${table.entityName? cap_first}${all.service.targetSuffix} service;
    
    @Before
    public void setUp() throws Exception {
        //TODO
        service.insertSelective(record);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int result = service.deleteByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertEquals(result, 1);
        
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNull(record);
    }

    @Test
    public void testInsert() {
        record.setId(null);
        //TODO
        int result = service.insert(record);
        Assert.assertEquals(result, 1);
        
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNotNull(record);
    }

    @Test
    public void testInsertSelective() {
        record.setId(null);
        //TODO
        int result = service.insertSelective(record);
        Assert.assertEquals(result, 1);
        
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNotNull(record);
    }

    @Test
    public void testSelectByPrimaryKey() {
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNotNull(record);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        //TODO
        int result = service.updateByPrimaryKeySelective(record);
        Assert.assertEquals(1, result);
        
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNotNull(record);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        //TODO
        int result = service.updateByPrimaryKeySelective(record);
        Assert.assertEquals(1, result);
        
        record = service.selectByPrimaryKey(record.get${table.primaryColumn.name? cap_first}());
        Assert.assertNotNull(record);
    }

    @Test
    public void testSelectSelective() {
        ${table.entityName? cap_first}${all.entity.targetSuffix} data = new ${table.entityName? cap_first}${all.entity.targetSuffix}();
        List<${table.entityName? cap_first}${all.entity.targetSuffix}> list = service.selectSelective(data);
        Assert.assertNotNull(list);
    }
    
    @Test
    public void testPage() {
        ${table.entityName? cap_first}${all.viewObject.targetSuffix} search = new ${table.entityName? cap_first}${all.viewObject.targetSuffix}();
        search.setRemark(REMARK);
        PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}> page = new PageVO<${table.entityName? cap_first}${all.viewObject.targetSuffix}>();
        page.setPageSize(10);
        page.setPageNum(1);
        page.setSearch(search);
        Page<${table.entityName? cap_first}${all.entity.targetSuffix}>records = service.page(page);
        Assert.assertNotNull(records);
        Assert.assertEquals(1, records.size());
        Assert.assertEquals(REMARK, records.get(0).getRemark());
    }
    
    @Test
    public void testSelectByPrimarys() {
        List<${table.entityName? cap_first}${all.entity.targetSuffix}>records = service.selectByPrimarys(Collections.singletonList(record.get${table.primaryColumn.name? cap_first}()));
        Assert.assertNotNull(records);
        Assert.assertEquals(1, records.size());
        Assert.assertEquals(REMARK, records.get(0).getRemark());
    }

}