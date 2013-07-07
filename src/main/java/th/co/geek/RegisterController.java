package th.co.geek;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import th.co.geek.model.Register;

@Controller
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	
	@RequestMapping(value="/register",   method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("registerForm")@Valid Register register, BindingResult result) {
		System.out.println(register);
		
		if(!register.getConfirmPassword().equals(register.getPassword())){
			result.addError(new ObjectError("password", "Password do not match."));
		}
		
		if(register.getUserName().length() > 20){
			result.addError(new ObjectError("username", "Username length over limit 20."));
		}
		
		if(register.getPassword().length() < 6){
			result.addError(new ObjectError("password", "Password length must more 6 ."));
		}
		
		if (result.hasErrors()) {
			
			return new ModelAndView("registerForm");
		}
		return new ModelAndView("registerSuccess","register",register);
	}
	
	@RequestMapping(value = "/register",  method = RequestMethod.GET)
	public String register() {
		return "registerForm";
	}
	
	
	
	
	@ModelAttribute("registerForm")
	public Register registerForm() {
		Register regis = new Register();
		 
		return regis;
	}
	
	
	
	
	

	
	
}