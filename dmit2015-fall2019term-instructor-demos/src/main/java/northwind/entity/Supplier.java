package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Suppliers database table.
 * 
 */
@Entity
@Table(name="Suppliers")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SupplierID")
	private int supplierID;

	@Column(name="Address")
	private Object address;

	@Column(name="City")
	private Object city;

	@Column(name="CompanyName")
	private Object companyName;

	@Column(name="ContactName")
	private Object contactName;

	@Column(name="ContactTitle")
	private Object contactTitle;

	@Column(name="Country")
	private Object country;

	@Column(name="Fax")
	private Object fax;

	@Column(name="HomePage")
	private Object homePage;

	@Column(name="Phone")
	private Object phone;

	@Column(name="PostalCode")
	private Object postalCode;

	@Column(name="Region")
	private Object region;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="supplier")
	private List<Product> products;

	public Supplier() {
	}

	public int getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public Object getAddress() {
		return this.address;
	}

	public void setAddress(Object address) {
		this.address = address;
	}

	public Object getCity() {
		return this.city;
	}

	public void setCity(Object city) {
		this.city = city;
	}

	public Object getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(Object companyName) {
		this.companyName = companyName;
	}

	public Object getContactName() {
		return this.contactName;
	}

	public void setContactName(Object contactName) {
		this.contactName = contactName;
	}

	public Object getContactTitle() {
		return this.contactTitle;
	}

	public void setContactTitle(Object contactTitle) {
		this.contactTitle = contactTitle;
	}

	public Object getCountry() {
		return this.country;
	}

	public void setCountry(Object country) {
		this.country = country;
	}

	public Object getFax() {
		return this.fax;
	}

	public void setFax(Object fax) {
		this.fax = fax;
	}

	public Object getHomePage() {
		return this.homePage;
	}

	public void setHomePage(Object homePage) {
		this.homePage = homePage;
	}

	public Object getPhone() {
		return this.phone;
	}

	public void setPhone(Object phone) {
		this.phone = phone;
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

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setSupplier(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setSupplier(null);

		return product;
	}

}