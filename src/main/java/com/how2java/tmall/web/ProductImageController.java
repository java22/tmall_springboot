package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 凌风的MI
 * 产品图片对应的控制器
 */
@RestController
public class ProductImageController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    CategoryService categoryService;

    /**
     * 显示图片
     * @param type 产品图片的类型(单个图片和详情图片)
     * @param pid 产品对应的id
     * @return 获取产品对应的产品图片
     */
    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type, @PathVariable("pid") int pid) throws Exception{
        Product product = productService.get(pid);

        if (ProductImageService.TYPE_SINGLE.equals(type)){
            List<ProductImage> singles = productImageService.listSingleProductImages(product);
            return singles;
        }else if (ProductImageService.TYPE_DETAIL.equals(type)){
            List<ProductImage> details = productImageService.listDetailProductImages(product);
            return details;
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * 添加图片
     * @param pid 添加产品图片对应的产品id
     * @param type 添加产品图片的类型
     * @param image 上传的图片
     */
    @PostMapping("/productImages")
    public Object add(@RequestParam("pid") int pid, @RequestParam("type") String type, MultipartFile image, HttpServletRequest request) throws  Exception{
        ProductImage bean = new ProductImage();
        Product product = productService.get(pid);
        bean.setProduct(product);
        bean.setType(type);

        productImageService.add(bean);

        String folder = "img/";
        if (ProductImageService.TYPE_SINGLE.equals(bean.getType())){
            folder += "productSingle";
        }else {
            folder += "productDetail";
        }
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder, bean.getId()+".jpg");
        String fileName = file.getName();
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "img", file);
        }catch (IOException e){
            e.printStackTrace();
        }

        if (ProductImageService.TYPE_SINGLE.equals(bean.getType())){
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }

        return bean;
    }

    /**
     * 删除图片
     * @param id 要删除的图片id
     */
    @DeleteMapping("productImages/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{
        ProductImage bean = productImageService.get(id);
        productImageService.delete(id);

        String folder = "img/";
        if (ProductImageService.TYPE_SINGLE.equals(bean.getType())){
            folder += "productSingle";
        }else {
            folder += "productDetail";
        }

        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder, bean.getId()+".jpg");
        String fileName = file.getName();
        file.delete();
        if (ProductImageService.TYPE_SINGLE.equals(bean.getType())){
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();
        }

        return null;
    }
}
