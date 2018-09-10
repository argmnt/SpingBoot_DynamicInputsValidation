package com.example.dynamic_validation.demodynamicvalidation.controller;

import com.example.dynamic_validation.demodynamicvalidation.model.Bibliotheca;
import com.example.dynamic_validation.demodynamicvalidation.model.Book;
import com.example.dynamic_validation.demodynamicvalidation.validator.BibliothecaValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created on September, 2018
 *
 * @author yagiz
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class BibliothecaController {

	private final BibliothecaValidator bibliothecaValidator;

	@InitBinder
	void setBibliothecaValidator(WebDataBinder binder) {
		binder.addValidators(bibliothecaValidator);
	}

	@GetMapping
	String newBibliotheca(Model model) {
		model.addAttribute("bibliotheca", new Bibliotheca(new Book()));
		return "newBibliotheca";
	}

	@PostMapping("bibliothecas")
	String createBibliotheca(@Valid Bibliotheca bibliotheca, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "newBibliotheca";
		}
		return "redirect:/";
	}
}
