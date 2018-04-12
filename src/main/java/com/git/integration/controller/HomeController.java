package com.git.integration.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import com.git.integration.domain.Configuration;

@Controller
public class HomeController {
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@RequestMapping(value = "/")
	public String actionDefault(Model model, Configuration configuration,  HttpServletRequest request, HttpServletResponse response) {
		
		String language = configuration.getLanguage();
		if (language != null) {
			String[] strings = language.split("_");
			Locale locale = new Locale(strings[0], strings[1]);
			localeResolver.setLocale(request, response, locale);
		}else {
			Locale locale = localeResolver.resolveLocale(request);
			configuration.setLanguage(locale.toString());
		}
		
		model.addAttribute("configuration", configuration);
		return "home";
	}

}
