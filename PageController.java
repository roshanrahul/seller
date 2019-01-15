package com.lti.shopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lti.shopping.model.Seller;
/*import com.lti.shopping.DAO.CategoryDAO;
import com.lti.shopping.model.Category;*/
import com.lti.shopping.model.UserDetails;
import com.lti.shopping.service.SellerService;
import com.lti.shopping.service.UserService;


@Controller
public class PageController {
	
	private UserService userService;
	
	private SellerService sellerService;

	@Autowired
	public void setUserService(UserService ps) {
		this.userService = ps;
	}
	@Autowired
	/* private CategoryDAO categoryDAO; */
	
	@RequestMapping(value={"/"})
	public ModelAndView goHome() {
		ModelAndView mv=new ModelAndView("index.jsp");
	return mv;
	}
	
	
	//for user
 	
	@RequestMapping(value="/login")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("login");
		return mv;
	}
	
 
	
	@RequestMapping(value="/loginverification",method=RequestMethod.POST)
	public String LoginValidation(Model model,HttpServletRequest req)
	{
		String username=req.getParameter("email");
		String password=req.getParameter("password");
		
		System.out.println("this is password"+username);
		System.out.println("this is password"+password);
		
		if(username.equals("admin@gmail.com")&& password.equals("admin"))
	 
		{
			return "admin";
		
		}
		else if(userService.verifyUser(username, password))
		{
			
			return "redirect:/";
		}
		else
		return "login";

	}

	
	@RequestMapping(value="/register")
	public String register(Model m) {
		m.addAttribute("user", new UserDetails());	 
		return "register";
	}
	
	@RequestMapping(value="/register/add" ,method = RequestMethod.POST)
		public String addUser(@ModelAttribute("user") UserDetails u,BindingResult result, Model model) {
	try {
		if (!result.hasErrors()) {			
			if (u.getId() == null || u.getId() == 0) {
				 
					this.userService.addUser(u);
			}  
		 
			return "redirect:/login";
		}
	} catch (Exception e) {
		 
		e.printStackTrace();
	}
	 
	return "register";
	}
	
	
	//for seller
	
	@RequestMapping(value="/seller")
	public String registerSeller(Model m) {
		m.addAttribute("user", new Seller());	 
		return "seller";
	}
	
	
	@RequestMapping(value="/selleradd" ,method = RequestMethod.POST)
	public String addSeller(@ModelAttribute("seller") Seller s,BindingResult result, Model model) {
try {
	if (!result.hasErrors()) {			
		if (s.getId() == null || u.getId() == 0) {
			 
				this.sellerService.add;
		}  
	 
		return "redirect:/login";
	}
} catch (Exception e) {
	 
	e.printStackTrace();
}
 
return "register";
}
	
	
//	@RequestMapping(value="/seller_login")
//	public ModelAndView seller() {
//		ModelAndView mv=new ModelAndView("seller_login");
//		mv.addObject("title","Sell");
//		mv.addObject("userClickSell",true);
//		return mv;
//	}
//	
//	@RequestMapping(value="/registerPage",method=RequestMethod.POST)
//	public String validateregistrationPage(@Valid @ModelAttribute("authuser") 
//	User authuser ,BindingResult bindingResult,Model model,HttpServletRequest req)
//	{
//		
//		String view="";
//	if(bindingResult.hasErrors())
//	{
//		view="success";
//		return view;
//	}
//	else
//	{
//		String username=req.getParameter("userEmail");
//		String password=req.getParameter("password");
//	
//	
//		userService.addUser(authuser);
//		
//		view="success";
//		return view;
//		
//	}
//	}
	

	
//	@RequestMapping(value="/Television")
//	public ModelAndView Television() {
//		ModelAndView mv=new ModelAndView("Products");
//		mv.addObject("title","Category TV");
//		mv.addObject("userClickTelevision",true);
//		return mv;
//	}
//	// method to load products based on categories
//	@RequestMapping(value="/show/all/products")
//	public ModelAndView showAllPoducts() {
//		ModelAndView mv=new ModelAndView("page");
//		mv.addObject("title","All Products");
//		
//		//pass list of categories
//		mv.addObject("categories",categoryDAO.list());
//		mv.addObject("userClickAllProducts",true);
//		return mv;
//	}
//	
//	@RequestMapping(value="/show/{id}/products")
//	public ModelAndView showCategoryPoducts(@PathVariable("id") int id) {
//		ModelAndView mv=new ModelAndView("page");
//		
//		//categorydao to fetch a single category
//		Category category=null;
//		category=categoryDAO.get(id);
//		mv.addObject("title","All Products");
//		
//		//pass list of categories
//		mv.addObject("categories",categoryDAO.list());
//		mv.addObject("userClickAllProducts",true);
//		return mv;
//	}
	
}
