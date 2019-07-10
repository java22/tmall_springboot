package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductImageDAO;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 产品图片对应的业务层
 */
@Service
public class ProductImageService {

    /**
     * 表示单个图片
     */
    public  static final String TYPE_SINGLE = "single";
    /**
     * 表示详情图片
     */
    public  static final String TYPE_DETAIL = "detail";

    @Autowired
    ProductImageDAO productImageDAO;
    @Autowired
    ProductService productService;

    /**
     * 增加图片
     */
    public void add(ProductImage bean){
        productImageDAO.save(bean);
    }

    /**
     * 删除图片
     */
    public void delete(int id){
        productImageDAO.delete(id);
    }

    /**
     * 获取图片
     */
    public ProductImage get(int id){
        return productImageDAO.findOne(id);
    }

    /**
     * 显示每个产品的单个图片
     */
    public List<ProductImage> listSingleProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, TYPE_SINGLE);
    }

    /**
     * 显示每个产品的详情图片
     */
    public List<ProductImage> listDetailProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, TYPE_DETAIL);
    }

    /**
     * 给第一个产品设置单个图片
     */
    public void setFirstProductImage(Product product){
        List<ProductImage> singleImages = listSingleProductImages(product);

        if (!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
        }
        else {
            /*这样做是考虑到产品还没有来得及设置图片,但是在订单后台管理查看订单项的对应的产品图片*/
            product.setFirstProductImage(new ProductImage());
        }
    }

    /**
     * 给第一个产品设置详情图片
     */
    public void setFirstProductImages(List<Product> products){
        for (Product product : products) {
            setFirstProductImage(product);
        }
    }

    /**
     * 在订单项中设置第一张图片显示
     */
    public void setFirstProdutImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }
}
