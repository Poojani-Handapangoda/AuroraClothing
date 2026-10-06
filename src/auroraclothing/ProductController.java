/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.util.List;

public class ProductController {

    // ==============================
    // GET ALL PRODUCTS
    // ==============================
    public List<Object[]> getAllProducts() {
        return ProductDAO.getAllProducts();
    }

    // ==============================
    // GET ONE PRODUCT
    // ==============================
    public Object[] getProductById(String productId) {
        return ProductDAO.getProductById(productId);
    }

    // ==============================
    // ADD PRODUCT
    // ==============================
    public boolean addProduct(Product product) {

        return ProductDAO.addProduct(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus()
        );
    }

    // ==============================
    // UPDATE PRODUCT
    // ==============================
    public boolean updateProduct(Product product) {

        return ProductDAO.updateProduct(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus()
        );
    }

    // ==============================
    // DELETE PRODUCT
    // ==============================
    public boolean deleteProduct(String productId) {
        return ProductDAO.deleteProduct(productId);
    }

    // ==============================
    // CHECK PRODUCT ID
    // ==============================
    public boolean productExists(String productId) {
        return ProductDAO.productExists(productId);
    }
}