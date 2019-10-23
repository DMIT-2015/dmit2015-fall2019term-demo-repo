package northwind.repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import northwind.entity.Order;

public class OrderRepository extends AbstractNorthwindJpaRepository<Order> {

	public OrderRepository() {
		super(Order.class);
	}
	
	public Order findOneOrder(int orderID) {
		Order singleResult = null;
		try {
			singleResult = getEntityManager().createQuery(
				"FROM Order o JOIN FETCH o.orderDetails "
				+ " WHERE o.orderID = :idValue"
				,Order.class)
				.setParameter("idValue", orderID)
				.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			// do nothing
			e.printStackTrace();
		}
		
		return singleResult;
	}
}