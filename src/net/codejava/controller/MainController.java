package net.codejava.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Lists;

import net.codejava.Util.Util;
import net.codejava.cart.CartInfo;
import net.codejava.cart.CartLine;
import net.codejava.cart.ProductInfo;
import net.codejava.model.Category;
import net.codejava.model.Customer;
import net.codejava.model.OrderInfo;
//import net.codejava.model.Customer;
import net.codejava.model.Product;
import net.codejava.model.ProductType;
import net.codejava.model.Users;
import net.codejava.service.CategoryService;
import net.codejava.service.CustomerService;
//import net.codejava.service.CustomerService;
import net.codejava.service.ProductService;
import net.codejava.service.ProductTypeService;
import net.codejava.service.UserService;

@Controller
@SessionAttributes("Users")
public class MainController {
	private Customer infoCustomer;
	private String idUser = "";
	private String username = "";
	private String userRole = "";

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductTypeService productType;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

//	@RequestMapping("/")
//	public ModelAndView home() {
//		List<Customer> listCustomer = customerService.listAll();
//		System.out.println(listCustomer);
//		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("listCustomer", listCustomer);
//		return mav;
//	}

	public Model getListCateProdtype(Model model) {

		List<ProductType> listProductType = productType.listAll();
		for (int i = 0; i < listProductType.size(); i++) {
			long idproducttype = listProductType.get(i).getId();
			String listCategories = "Listcategory" + i;
			List<Category> listCategory = categoryService.listCategoryFromProductType(idproducttype);
			model.addAttribute(listCategories, listCategory);
		}
		model.addAttribute("listProductTypes", listProductType);
		return model;

	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/Shop")
	public String Shop(Model model, HttpServletResponse response, HttpServletRequest request) {
		List<Product> listProducts = productService.listAll();
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("listProducts", listProducts);
		return "Shop";
	}

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) throws IOException {
		Product product = null;
		if (id != null) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null && product.getImg() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImg());
		}
		response.getOutputStream().close();
	}

	@RequestMapping("/prodCate")
	public String prodCate(Model model, @RequestParam("id") long id, HttpServletRequest request,
			HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		List<Product> prodCate = (List<Product>) productService.listProdCate(id);
		model.addAttribute("listProd", prodCate);

		return "listProd";
	}

	@RequestMapping("/prodType")
	public String prodType(Model model, @RequestParam("id") long id, HttpServletRequest request,
			HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		List<Product> prodType = (List<Product>) productService.listProdType(id);
		model.addAttribute("listProd", prodType);

		return "listProd";
	}

	@RequestMapping("/DetailProd")
	public String prodDetail(Model model, @RequestParam("id") long id, HttpServletRequest request,
			HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		List<Product> prodCate = (List<Product>) productService.findbyid(id);
		model.addAttribute("listProd", prodCate);

		return "listProd";
	}

	@RequestMapping("/addToCart")
	public String addToCart(Model model, @RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}
		return "redirect:/shoppingCart";

	}

	@RequestMapping("/shoppingCart")
	public String getShoppingCart(Model model, HttpServletRequest request, HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		CartInfo myCart = Util.getCart();
		model.addAttribute("Cart", myCart);
		return "shoppingCart";
	}

	@RequestMapping("/removeProdCart")
	public String removeProdCart(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.removeProd(productInfo);
		}
		return "redirect:/shoppingCart";
	}

	@RequestMapping("/plusProdCart")
	public String plusProdCart(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}
		return "redirect:/shoppingCart";

	}

	@RequestMapping("/botSoLuong")
	public String botSoLuong(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.botSoLuong(productInfo);
		}
		return "redirect:/shoppingCart";

	}

	@RequestMapping("/plusProdCartFinal")
	public String plusProdCartFinal(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}
		return "redirect:/FinalizeCheckOut";

	}

	@RequestMapping("/botSoLuongFinal")
	public String botSoLuongFinal(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.botSoLuong(productInfo);
		}
		return "redirect:/FinalizeCheckOut";

	}

	@RequestMapping("/removeProdCartFinal")
	public String removeProdCartFinal(Model model, @RequestParam("id") String id) {
		Product product = null;
		if (id != null && id.length() > 0) {
			product = productService.get(Long.parseLong(id));
		}
		if (product != null) {
			CartInfo cartInfo = Util.getCart();
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.removeProd(productInfo);
		}
		return "redirect:/FinalizeCheckOut";
	}

	@RequestMapping("/CartConfirminfomation")
	public String CofirmInfoCustomer(Model model) {
		getListCateProdtype(model);
		if (infoCustomer != null) {
			model.addAttribute("Customer", infoCustomer);
			CartInfo myCart = Util.getCart();
			model.addAttribute("Cart", myCart);
			return "FinalizeCheckOut";
		} else {
			model.addAttribute("Customer", new Customer());
			return "ConfirmInfoCustomer";
		}

	}

	@RequestMapping("/SaveInfoCustomer")
	public String saveCustomer(@ModelAttribute("Customer") Customer customer, Model model) {
		getListCateProdtype(model);
		infoCustomer = customer;
		CartInfo myCart = Util.getCart();
		model.addAttribute("Cart", myCart);
		return "FinalizeCheckOut";
	}

	@RequestMapping("/FinalizeCheckOut")
	public String FinalizeCheckOut(@ModelAttribute("Customer") Customer customer, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		if (infoCustomer != null) {
			model.addAttribute("Customer", infoCustomer);
			CartInfo myCart = Util.getCart();
			model.addAttribute("Cart", myCart);
			return "FinalizeCheckOut";
		} else {
			model.addAttribute("Customer", new Customer());
			return "ConfirmInfoCustomer";
		}
	}

	@RequestMapping("/FinalCheckOut")
	public String FinalCheckOut(@ModelAttribute("Customer") Customer customer, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		CartInfo myCart = Util.getCart();
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		for (int i = 0; i < myCart.getCartLines().size(); i++) {
			OrderInfo orderInfo = new OrderInfo();
			Product product = productService.get(myCart.getCartLines().get(i).getProductInfo().getId());
			orderInfo.setProduct(product);
			orderInfo.setQuantity(myCart.getCartLines().get(i).getQuantity());
			orderInfos.add(orderInfo);
		}
		customer.setOrderInfos(Lists.newArrayList(orderInfos));
		customerService.saveAndFlush(customer);
		Util.removeCart();
		return "redirect:/PaySuccess";
	}

	@RequestMapping("/PaySuccess")
	public String PaySuccess(@ModelAttribute("Customer") Customer customer, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("Customer", infoCustomer);
		model.addAttribute("ListOrder", customerService.getlistOrder(customerService.getidLastCustomer()));
		return "PaySuccess";
	}

	@ModelAttribute("Users")
	public Users setUpUserForm() {
		return new Users();

	}

	@RequestMapping("/Login")
	public String LoginPage(Model model, HttpServletResponse response, HttpServletRequest request) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);

		if (!idUser.equals("")) {
			model.addAttribute("message", "You are already logged in");
			return "redirect:/Shop";
		}
		return "Login";

	}

//	
	private Model getCookieLogin(Model model, HttpServletResponse response, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return model;
		}
		for (Cookie ck : cookies) {
			if (ck.getName().equals("setUser")) {
				model.addAttribute("cookieValue", ck);
				username = "";
				username = ck.getValue();
			}
			if (ck.getName().equals("idUser")) {
				model.addAttribute("idUser", ck);
				idUser = "";
				idUser = ck.getValue();
			}
			if (ck.getName().equals("UserRoleCookie")) {
				model.addAttribute("UserRoleCookie", ck);
				userRole = "";
				userRole = ck.getValue();
			}
		}

		return model;

	}

	@PostMapping("/CheckLogin")
	public String doLogin(@ModelAttribute("Users") Users user, Model model,
			@CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletResponse response,
			HttpServletRequest request) throws UnsupportedEncodingException {
		getListCateProdtype(model);
		List<Users> listUser = userService.listUser(user.getUsername());

		if (user.getUsername().equals("")) {
			user.setUsername("");
			Cookie cookie = new Cookie("setUser", setUser);
			model.addAttribute("cookieValue", cookie);
			model.addAttribute("message", "Login failed. Try again.");
			return "Login";
		}
		// implement business logic
		if (user.getUsername().equals(listUser.get(0).getUsername())
				&& user.getPass_word().equals(listUser.get(0).getPass_word())) {
			if (user.getUsername() != null) {
				setUser = user.getUsername();
				// create cookie and set it in response
				Cookie cookie = new Cookie("setUser", setUser);
				cookie.setMaxAge(24 * 60 * 60);
				response.addCookie(cookie);
				Cookie IdUserCookie = new Cookie("idUser", String.valueOf(listUser.get(0).getId()));
				IdUserCookie.setMaxAge(24 * 60 * 60);
				response.addCookie(IdUserCookie);
				List<String> listuserRole = userService.getRole(listUser.get(0).getId());
				for (int i = 0; i < listuserRole.size(); i++) {
					System.out.println("USER ROLE " + listuserRole.get(i));
					if (listuserRole.get(i).equals("ROLE_ADMIN")) {
						userRole = "ROLE_ADMIN";
						Cookie RoleUserCookie = new Cookie("UserRoleCookie", userRole);
						model.addAttribute("UserRoleCookie", RoleUserCookie);
						RoleUserCookie.setMaxAge(24 * 60 * 60);
						response.addCookie(RoleUserCookie);
					}
				}
				List<Product> listProducts = productService.listAll();
				getListCateProdtype(model);
				model.addAttribute("cookieValue", cookie);
				model.addAttribute("listProducts", listProducts);
				model.addAttribute("message", "Login success. Welcome " + listUser.get(0).getFullname());
				return "Shop";
			}
		} else {
			user.setUsername("");
			Cookie cookie = new Cookie("setUser", setUser);
			model.addAttribute("cookieValue", cookie);
			model.addAttribute("message", "Login failed. Try again.");
			return "Login";
		}
		return "Login";

	}

	@RequestMapping("/Logout")
	public String doLogout(Model model, HttpServletResponse response, HttpServletRequest request) {
		getListCateProdtype(model);
		Cookie cookie = new Cookie("setUser", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		Cookie IdUserCookie = new Cookie("idUser", null);
		IdUserCookie.setMaxAge(0);
		response.addCookie(IdUserCookie);
		Cookie UserRoleCookie = new Cookie("UserRoleCookie", null);
		UserRoleCookie.setMaxAge(0);
		response.addCookie(UserRoleCookie);
		model.addAttribute("message", "Logout Success");
		idUser = "";
		username = "";
		userRole = "";
		return "Login";
	}

	@RequestMapping("/EditInfomation")
	public String EditInfomation(@ModelAttribute("Users") Users user, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		return "EditInfomation";
	}

	@RequestMapping("/SaveInfoUser")
	public String SaveInfomationUser(@ModelAttribute("Users") Users user, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		user.setUsername(username);
		userService.saveUser(user.getPass_word(), user.getAddress(), user.getEmail(), user.getFullname(),
				user.getPhone(), user.getUsername(), idUser);
		model.addAttribute("message", "Your information has been saved successfully ");
		return "EditInfomation";
	}

	@RequestMapping("/AdminManager")
	public String doAdminManager(@ModelAttribute("Category") Category Category,@ModelAttribute("ProductType") ProductType prodType,Model model, HttpServletResponse response, HttpServletRequest request) {
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("ProductType", prodType);
		model.addAttribute("Category", Category);
		return "AdminManager";
	}
	
	@RequestMapping("/addTypeProd")
	public String editProdType(@ModelAttribute("Category") Category Category,@ModelAttribute("ProductType") ProductType prodType,Model model, HttpServletResponse response, HttpServletRequest request) {
		productType.save(prodType);
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("ProductType", prodType);
		model.addAttribute("Category", Category);
		model.addAttribute("message", "Thêm mới danh mục thành công ");
		return "AdminManager";
	}
	@RequestMapping("/delTypeProd")
	public String delProdType(@ModelAttribute("Category") Category Category,@ModelAttribute("ProductType") ProductType prodType, @RequestParam long id,Model model, HttpServletResponse response, HttpServletRequest request) {
		productType.delete(id);
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("ProductType", prodType);
		model.addAttribute("Category", Category);
		model.addAttribute("message", "Xóa danh mục thành công");
		return "AdminManager";
	}
	
	@RequestMapping("/EditTypeProd")
	public String editProdType(@ModelAttribute("ProductType") ProductType prodType, @ModelAttribute("Category") Category Category, @RequestParam long id,Model model, HttpServletResponse response, HttpServletRequest request) {
		productType.editProdType(prodType.getProducttype(), id);
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("ProductType", prodType);
		model.addAttribute("Category", Category);
		model.addAttribute("message", "Sửa danh mục thành công");
		return "AdminManager";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(@ModelAttribute("Category") Category Category,@ModelAttribute("ProductType") ProductType prodType,Model model, HttpServletResponse response, HttpServletRequest request) {
		categoryService.save(Category);
		getListCateProdtype(model);
		getCookieLogin(model, response, request);
		model.addAttribute("ProductType", prodType);
		model.addAttribute("Category", Category);
		model.addAttribute("message", "Thêm mới danh mục thành công ");
		return "AdminManager";
	}

//	

//	@RequestMapping("/user")
//	public String user(Model model) {
//		List<Users> listUsers = usersService.listAll();
//		model.addAttribute("listUsers", listUsers);
//		return "user";
//	}

//	@RequestMapping("/new")
//	public String newCustomerForm(Map<String, Object> model) {
//		Customer customer = new Customer();
//		model.put("customer", customer);
//		return "new_customer";
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
//		customerService.save(customer);
//		return "redirect:/";
//	}
//
//	@RequestMapping("/edit")
//	public ModelAndView editCustomerForm(@RequestParam long id) {
//		ModelAndView mav = new ModelAndView("edit_customer");
//		Customer customer = customerService.get(id);
//		mav.addObject("customer", customer);
//
//		return mav;
//	}
//
//	@RequestMapping("/delete")
//	public String deleteCustomerForm(@RequestParam long id) {
//		customerService.delete(id);
//		return "redirect:/";
//	}
//
	@RequestMapping("/search")
	public ModelAndView search(Model model, @RequestParam String keyword) {
		List<Product> result = productService.search(keyword);
		getListCateProdtype(model);
		ModelAndView mav = new ModelAndView("listProd");
		mav.addObject("listProd", result);

		return mav;
	}
}
