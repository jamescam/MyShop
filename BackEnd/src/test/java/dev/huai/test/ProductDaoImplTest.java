package dev.huai.test;
import dev.huai.daos.ProductDao;
import dev.huai.daos.ProductDaoImpl;
import dev.huai.models.Product;
import org.junit.Test;

import java.math.BigDecimal;
import static junit.framework.TestCase.*;

public class ProductDaoImplTest {
    private ProductDao productDao = new ProductDaoImpl();

    @Test
    public void testAddProduct(){
        BigDecimal price = new BigDecimal("0.99");
        Product product = new Product();
        product.setProductPrice(price);
        product.setProductName("Dragon fruit");
        assertTrue(productDao.addProduct(product));
    }


    @Test
    public void testUpdateProduct(){
        assertTrue(productDao.updateProductPrice(2, new BigDecimal(2.99)));
    }

}
