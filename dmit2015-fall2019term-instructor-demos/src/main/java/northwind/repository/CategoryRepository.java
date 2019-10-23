package northwind.repository;

import java.util.List;

import northwind.entity.Category;

public class CategoryRepository extends AbstractNorthwindJpaRepository<Category> {

	public CategoryRepository() {
		super(Category.class);
	}

	public List<Category> findAllOrderByCategoryName() {
		return getEntityManager().createQuery(
			"FROM Category c ORDER BY c.categoryName",
			Category.class)
			.getResultList();
	}
}