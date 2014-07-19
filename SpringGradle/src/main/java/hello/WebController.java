package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(Person person) {
        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView checkPersonInfo(@Valid Person person, BindingResult bindingResult, Model model) {
        
    	ModelAndView modelAndView = null;
    	if (bindingResult.hasErrors()) {
    		modelAndView = new ModelAndView("form");
    		return modelAndView;
        }
        modelAndView = new ModelAndView("results");
        modelAndView.addObject("person", person);        
        return modelAndView;
    }

}