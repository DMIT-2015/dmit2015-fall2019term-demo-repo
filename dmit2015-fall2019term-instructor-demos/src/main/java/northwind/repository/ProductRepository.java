package northwind.repository;

import northwind.entity.Product;

public class ProductRepository extends AbstractNorthwindJpaRepository<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	
}