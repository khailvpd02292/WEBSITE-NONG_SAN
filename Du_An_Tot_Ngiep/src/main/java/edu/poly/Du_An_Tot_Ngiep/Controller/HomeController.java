package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@Controller
@RequestMapping(value = "/index")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	

	@GetMapping()
	public String Home(ModelMap model) {
		model.addAttribute("prods", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "home/index";
	}

	@GetMapping("/product")
	public String ShowListProduct(ModelMap model) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "shop/shop";
	}

	@GetMapping("/about")
	public String ShowAbout(ModelMap model) {
		return "shop/about";
	}

	@GetMapping("/contact")
	public String ShowContact(ModelMap model) {
		return "shop/contact";
	}

	// code showCategoryById
	@GetMapping(value = "/showProductByIdCategory/{idCategory}")
	public String ShowProductByIdCategory(ModelMap model, @PathVariable("idCategory") int idCategory) {
		
		Optional<Product> p = this.productService.findById(idCategory);
		if (p == null) {
			return "shop/productByIdCategory";
		}
		model.addAttribute("showProductByIdCategory", this.productService.showListProductByIdCategory(idCategory));
		
		return "shop/productByIdCategory";
	}
	
	@GetMapping(value = "/showProductSingle/{idProduct}")
	public String ShowProductByIdProductDetail(ModelMap model, @PathVariable("idProduct") int id) {
		
		
		model.addAttribute("showProductSingle", this.productService.findById(id).get());
		
		return "shop/product-single";
	}
	
	@GetMapping(value = "/searchProduct")
	public String searchProductByIdCategory(ModelMap model, @RequestParam("key") String key, Product product){
		
		List<Product> products = this.productService.searchListProductByIdCategory(key);
		if (products.isEmpty() || products.contains(product)) {
			return "shop/searchProduct";
		}
		model.addAttribute("searchProduct", this.productService.searchListProductByIdCategory(key));
		
		return "shop/searchProduct";
	}
	
}
