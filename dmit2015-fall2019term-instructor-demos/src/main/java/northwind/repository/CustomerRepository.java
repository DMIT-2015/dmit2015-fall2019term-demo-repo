package northwind.repository;

import northwind.entity.Customer;

public class CustomerRepository extends AbstractNorthwindJpaRepository<Customer> {

	public CustomerRepository() {
		super(Customer.class);
	}

	
}