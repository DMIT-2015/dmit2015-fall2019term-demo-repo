package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Product;

@Stateless
public class ProductBean {
	
	@PersistenceContext(unitName = "northwind-jpa-pu")
	private EntityManager em;

	public void add(Product newProduct) {
		em.persist(newProduct);
	}
	
	public void update(Product existingProduct) {
		em.merge(existingProduct);
	}
	
	public void remove(Product existingProduct) {
		em.remove(existingProduct);
	}
	public void remove(int productID) {
		Product existingCategoy = findById(productID);
		remove(existingCategoy);
	}
	
	public Product findById(int productID) {
		return em.find(Product.class, productID);
	}
	
	public List<Product> findAll() {
		return em.createQuery(
				"SELECT c FROM Product c ORDER BY c.productName"
			, Product.class)
			.getResultList();
	}
}
