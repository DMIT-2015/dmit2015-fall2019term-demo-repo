package northwind.repository;

import northwind.entity.Employee;

public class EmployeeRepository extends AbstractNorthwindJpaRepository<Employee> {

	public EmployeeRepository() {
		super(Employee.class);
	}

	
}