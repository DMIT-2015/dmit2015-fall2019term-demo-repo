package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Employees database table.
 * 
 */
@Entity
@Table(name="Employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EmployeeID")
	private int employeeID;

	@Column(name="Address")
	private Object address;

	@Column(name="BirthDate")
	private Timestamp birthDate;

	@Column(name="City")
	private Object city;

	@Column(name="Country")
	private Object country;

	@Column(name="Extension")
	private Object extension;

	@Column(name="FirstName")
	private Object firstName;

	@Column(name="HireDate")
	private Timestamp hireDate;

	@Column(name="HomePhone")
	private Object homePhone;

	@Column(name="LastName")
	private Object lastName;

	@Column(name="Notes")
	private Object notes;

	@Lob
	@Column(name="Photo")
	private byte[] photo;

	@Column(name="PhotoPath")
	private Object photoPath;

	@Column(name="PostalCode")
	private Object postalCode;

	@Column(name="Region")
	private Object region;

	@Column(name="Title")
	private Object title;

	@Column(name="TitleOfCourtesy")
	private Object titleOfCourtesy;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="ReportsTo")
	private Employee employee;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="employee")
	private List<Employee> employees;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="employee")
	private List<Order> orders;

	//bi-directional many-to-many association to Territory
	@ManyToMany(mappedBy="employees")
	private List<Territory> territories;

	public Employee() {
	}

	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Object getAddress() {
		return this.address;
	}

	public void setAddress(Object address) {
		this.address = address;
	}

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Object getCity() {
		return this.city;
	}

	public void setCity(Object city) {
		this.city = city;
	}

	public Object getCountry() {
		return this.country;
	}

	public void setCountry(Object country) {
		this.country = country;
	}

	public Object getExtension() {
		return this.extension;
	}

	public void setExtension(Object extension) {
		this.extension = extension;
	}

	public Object getFirstName() {
		return this.firstName;
	}

	public void setFirstName(Object firstName) {
		this.firstName = firstName;
	}

	public Timestamp getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Timestamp hireDate) {
		this.hireDate = hireDate;
	}

	public Object getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(Object homePhone) {
		this.homePhone = homePhone;
	}

	public Object getLastName() {
		return this.lastName;
	}

	public void setLastName(Object lastName) {
		this.lastName = lastName;
	}

	public Object getNotes() {
		return this.notes;
	}

	public void setNotes(Object notes) {
		this.notes = notes;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Object getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(Object photoPath) {
		this.photoPath = photoPath;
	}

	public Object getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Object postalCode) {
		this.postalCode = postalCode;
	}

	public Object getRegion() {
		return this.region;
	}

	public void setRegion(Object region) {
		this.region = region;
	}

	public Object getTitle() {
		return this.title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public Object getTitleOfCourtesy() {
		return this.titleOfCourtesy;
	}

	public void setTitleOfCourtesy(Object titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);

		return employee;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setEmployee(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setEmployee(null);

		return order;
	}

	public List<Territory> getTerritories() {
		return this.territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

}