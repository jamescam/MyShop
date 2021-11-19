package dev.huai.services;

import dev.huai.daos.ProductDao;
import dev.huai.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public boolean updateProductPrice(Integer product_id, BigDecimal newPrice) {
        return productDao.updateProductPrice(product_id, newPrice);
    }

    @Override
    public boolean deleteProductById(Integer product_id) {
        return productDao.deleteProductById(product_id);
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
