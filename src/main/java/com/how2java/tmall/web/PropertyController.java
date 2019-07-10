package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 凌风的MI
 * 商品分类属性对应的控制器
 */
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    /**
     * 获取分类下对应的属性
     * @param cid 分类的id
     * @param start 起始页
     * @param size 每页显示的条数
     * @return 分页后的数据
     */
    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable("cid") int cid,
                                         @RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) throws  Exception{
        start = start<0 ? 0:start;
        Page4Navigator<Property> page = propertyService.list(cid, start, size, 5);
        return page;
    }

    /**
     * 获取属性
     * @param id 属性的id
     * @return 对应的数据
     */
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) throws Exception{
        Property bean = propertyService.get(id);
        return bean;
    }
    
    /**
     * 增加属性
     * @param bean 要增加的属性
     * @return  对应的数据
     */
    @PostMapping("/properties")
    public Object add(@RequestBody Property bean) throws Exception{
        propertyService.add(bean);
        return bean;
    }

    /**
     * 删除属性
     * @param id 要删除的属性id
     */
    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        propertyService.delete(id);
        return null;
    }

    /**
     * 修改属性
     * @param bean 要修改的属性
     * @return  修改后的数据
     */
    @PutMapping("/properties")
    public Object update(@RequestBody Property bean) throws Exception{
        propertyService.update(bean);
        return bean;
    }
}
