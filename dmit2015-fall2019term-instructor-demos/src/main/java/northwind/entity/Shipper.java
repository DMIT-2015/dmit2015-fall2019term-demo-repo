package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Shippers database table.
 * 
 */
@Entity
@Table(name="Shippers")
@NamedQuery(name="Shipper.findAll", query="SELECT s FROM Shipper s")
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ShipperID")
	private int shipperID;

	@Column(name="CompanyName")
	private Object companyName;

	@Column(name="Phone")
	private Object phone;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="shipper")
	private List<Order> orders;

	public Shipper() {
	}

	public int getShipperID() {
		return this.shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public Object getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(Object companyName) {
		this.companyName = companyName;
	}

	public Object getPhone() {
		return this.phone;
	}

	public void setPhone(Object phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setShipper(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setShipper(null);

		return order;
	}

}