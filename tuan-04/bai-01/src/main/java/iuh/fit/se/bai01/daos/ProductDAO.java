package iuh.fit.se.bai01.daos;

import iuh.fit.se.bai01.entities.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> findAll();

    public Product getById(int id);

    public void addProduct(Product p);
}
