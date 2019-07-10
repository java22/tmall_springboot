package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author 凌风的MI
 * 商品分类对应的控制器
 */
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 获取Page4Navigator对象集合
     * @param start 开始的位置
     * @param size 长度
     * @return  返回分页并排序好的结果
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size ) throws Exception {
        start = start < 0 ? 0 : start;
        //5表示导航分页最多有5个,像[1,2,3,4,5]这样
        Page4Navigator<Category> page = categoryService.list(start, size, 5);
        return page;
    }

    /**
     * 上传图片
     * @param bean 分类id
     * @param image 上传的图片
     * @return 返回插入成功的列表
     */
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        /*保存到数据库*/
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return  bean;
    }

    /**
     * 保存上传的图片
     */
    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException{
        /*将上传的图片保存到img/category*/
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        /*文件名使用新增分类的id*/
        File file = new File(imageFolder, bean.getId()+".jpg");
        /*判断目录不存在,则创建*/
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        /*进行文件复制*/
        image.transferTo(file);
        /*调用ImageUtil中的change2jpg转换为jpg格式*/
        BufferedImage img = ImageUtil.change2jpg(file);
        /*保存图片*/
        ImageIO.write(img, "jpg", file);
    }

    /**
     * 删除分类
     * @param id 要删除的节点id
     */
    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    /**
     * 编辑分类
     * @param id 要编辑的节点id
     * @return 返回对应的数据
     */
    @GetMapping("/categories/{id}")
    public  Category get(@PathVariable("id") int id) throws Exception{
        Category bean = categoryService.get(id);
        return bean;
    }

    /**
     * 实现真正的编辑分类
     * @param  bean 修改后的数据
     * @param image 上传的图片
     * @param request 获取参数
     * @return  修改后的数据
     */
    @PutMapping("/categories/{id}")
    public Object update(MultipartFile image, Category bean, HttpServletRequest request) throws Exception{
        categoryService.update(bean);

        if (image != null){
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}
