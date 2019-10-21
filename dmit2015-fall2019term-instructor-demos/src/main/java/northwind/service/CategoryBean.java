package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Category;

@Stateless
public class CategoryBean {
	
	@PersistenceContext(unitName = "northwind-jpa-pu")
	private EntityManager em;

	public void add(Category newCategory) {
		em.persist(newCategory);
	}
	
	public void update(Category existingCategory) {
		em.merge(existingCategory);
	}
	
	public void remove(Category existingCategory) {
		em.remove(existingCategory);
	}
	public void remove(int categoryID) {
		Category existingCategoy = findById(categoryID);
		remove(existingCategoy);
	}
	
	public Category findById(int categoryID) {
		return em.find(Category.class, categoryID);
	}
	
	public List<Category> findAll() {
		return em.createQuery(
				"SELECT c FROM Category c ORDER BY c.categoryName"
			, Category.class)
			.getResultList();
	}
}
