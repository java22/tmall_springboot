package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductDAO;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 凌风的MI
 * 产品对应的业务层
 */
@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    /**
     * 增加产品
     * @param bean 需要增加的产品属性
     */
    public void add(Product bean){
        productDAO.save(bean);
    }

    /**
     * 删除产品
     * @param id 删除的产品id
     */
    public void delete(int id){
        productDAO.delete(id);
    }

    /**
     * 获取产品
     * @param id 获取的产品id
     * @return  对应的数据
     */
    public Product get(int id){
        return productDAO.findOne(id);
    }

    /**
     * 修改产品
     * @param bean 要修改产品的属性
     */
    public void update(Product bean){
        productDAO.save(bean);
    }

    /**
     * 对分类中的产品进行分页
     * @param cid 对应的分类id
     * @param start 开始页
     * @param size 每页显示多少个
     * @param navigatePages 分页导航最多显示多少页
     * @return  分页后的产品属性
     */
    public Page4Navigator<Product> list(int cid,int start,int size,int navigatePages){
        Category category = categoryService.get(cid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Product> pageFromJPA = productDAO.findByCategory(category, pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /**
     * 为多个分类填充产品集合
     */
    public void fill(List<Category> categories){
        for (Category category : categories){
            fill(category);
        }
    }

    /**
     * 为分类填充产品集合
     */
    public void fill(Category category){
        List<Product> products = listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
    }

    /**
     * 为多个分类填充推荐产品集合
     * 即把分类下的产品集合,按照8个为一行,拆分成多行,便于后续页面进行展示
     */
    public void fillByRow(List<Category> categories){
        int productNumberEachRow = 8;
        for (Category category : categories){
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size = size>products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    /**
     * 查询某个分类下的所有产品(不分页)
     */
    public List<Product> listByCategory(Category category){
        return productDAO.findByCategoryOrderById(category);
    }

    /**
     * 为产品设置销量和评价数量
     */
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);

    }

    /**
     * 遍历每个产品,设置销量和评价数量
     */
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products){
            setSaleAndReviewNumber(product);
        }
    }

    /**
     * 模糊查询
     */
    public List<Product> search(String keyword, int start, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        List<Product> products =productDAO.findByNameLike("%"+keyword+"%",pageable);
        return products;
    }
}
