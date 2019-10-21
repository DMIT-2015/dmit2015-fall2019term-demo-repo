package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import northwind.entity.Category;
import northwind.service.CategoryBean;

@Named
@ViewScoped
public class ViewAllCategoryController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private CategoryBean currentCategoryBean;
	
	public List<Category> getAllCategory() {
		return currentCategoryBean.findAll();
	}
}
