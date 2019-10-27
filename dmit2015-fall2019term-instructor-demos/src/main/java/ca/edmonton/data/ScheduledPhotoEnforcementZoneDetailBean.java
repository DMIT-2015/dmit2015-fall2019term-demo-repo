package ca.edmonton.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ScheduledPhotoEnforcementZoneDetailBean {
	
	@PersistenceContext(unitName = "dmit2015-jpa-pu")
	private EntityManager entityManager;

	public List<ScheduledPhotoEnforcementZoneDetail> findAll() {
		return entityManager.createQuery(
			"SELECT z FROM ScheduledPhotoEnforcementZoneDetail z"
			, ScheduledPhotoEnforcementZoneDetail.class)
			.getResultList();
	}

	public List<ScheduledPhotoEnforcementZoneDetail> findAllOrderByRoadNameDescending() {
		return entityManager.createQuery(
			"SELECT z FROM ScheduledPhotoEnforcementZoneDetail z ORDER BY z.roadName DESC"
			, ScheduledPhotoEnforcementZoneDetail.class)
			.getResultList();
	}

	public List<ScheduledPhotoEnforcementZoneDetail> findAllByRoadName(String roadName) {
		return entityManager.createQuery(
			"SELECT z FROM ScheduledPhotoEnforcementZoneDetail z "
			+ " WHERE z.roadName = :roadNameValue "
			+ " ORDER BY z.roadName DESC "
			, ScheduledPhotoEnforcementZoneDetail.class)
			.setParameter("roadNameValue", roadName)
			.getResultList();
	}

	public List<ScheduledPhotoEnforcementZoneDetail> findAllBySpeedLimitAndRoadName(int speedLimit, String roadName) {
		return entityManager.createQuery(
			"SELECT z FROM ScheduledPhotoEnforcementZoneDetail z "
			+ " WHERE z.roadName = :roadNameValue AND z.speedLimit = :speedLimitValue "
			+ " ORDER BY z.roadName DESC "
			, ScheduledPhotoEnforcementZoneDetail.class)
			.setParameter("roadNameValue", roadName)
			.setParameter("speedLimitValue", speedLimit)
			.getResultList();
	}

	public List<ScheduledPhotoEnforcementZoneDetail> findAllBySpeedLimit(int speedLimit) {
		return entityManager.createQuery(
			"SELECT z FROM ScheduledPhotoEnforcementZoneDetail z "
			+ " WHERE z.speedLimit = :speedLimitValue "
			+ " ORDER BY z.speedLimit DESC "
			, ScheduledPhotoEnforcementZoneDetail.class)
			.setParameter("speedLimitValue", speedLimit)
			.getResultList();
	}

	public List<String> findDistinctRoadNames() {
		return entityManager.createQuery(
				"SELECT z.roadName FROM ScheduledPhotoEnforcementZoneDetail z "
				+ " GROUP BY z.roadName "
				+ " ORDER BY z.roadName "
				, String.class)
				.getResultList();
	}
	
	public List<Integer> findDistinctSpeedLimits() {
		return entityManager.createQuery(
				"SELECT z.speedLimit FROM ScheduledPhotoEnforcementZoneDetail z "
				+ " GROUP BY z.speedLimit "
				+ " ORDER BY z.speedLimit "
				, Integer.class)
				.getResultList();
	}
	
	
}
