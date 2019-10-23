package northwind.repository;

import java.util.List;

import northwind.entity.Shipper;

public class ShipperRepository extends AbstractNorthwindJpaRepository<Shipper> {

	public ShipperRepository() {
		super(Shipper.class);
	}

	public List<Shipper> findAllOrderByCompanyName() {
		return getEntityManager().createQuery(
			"FROM Shipper s ORDER BY s.companyName"
			,Shipper.class)
			.getResultList();
	}
}