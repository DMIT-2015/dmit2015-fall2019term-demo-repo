package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Territories database table.
 * 
 */
@Entity
@Table(name="Territories")
@NamedQuery(name="Territory.findAll", query="SELECT t FROM Territory t")
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TerritoryID")
	private String territoryID;

	@Column(name="TerritoryDescription")
	private String territoryDescription;

	//bi-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="EmployeeTerritories"
		, joinColumns={
			@JoinColumn(name="TerritoryID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="EmployeeID")
			}
		)
	private List<Employee> employees;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="RegionID")
	private Region region;

	public Territory() {
	}

	public String getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public String getTerritoryDescription() {
		return this.territoryDescription;
	}

	public void setTerritoryDescription(String territoryDescription) {
		this.territoryDescription = territoryDescription;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}