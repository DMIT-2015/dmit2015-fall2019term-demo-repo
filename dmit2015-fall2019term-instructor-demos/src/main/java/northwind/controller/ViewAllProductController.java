package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import northwind.entity.Product;
import northwind.service.ProductBean;

@Named
@ViewScoped
public class ViewAllProductController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductBean currentProductBean;
	
	public List<Product> getAllProduct() {
		return currentProductBean.findAll();
	}
}
