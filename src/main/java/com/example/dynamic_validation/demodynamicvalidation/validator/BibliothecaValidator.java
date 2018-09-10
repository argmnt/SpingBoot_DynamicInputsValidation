package com.example.dynamic_validation.demodynamicvalidation.validator;

import com.example.dynamic_validation.demodynamicvalidation.model.Bibliotheca;
import com.example.dynamic_validation.demodynamicvalidation.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created on September, 2018
 *
 * @author yagiz
 */
@Component
public class BibliothecaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Bibliotheca.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bibliotheca bibliotheca = (Bibliotheca) target;

		if (bibliotheca.getYearOfFoundation() != null && bibliotheca.getYearOfFoundation().isAfter(LocalDate.now())) {
			errors.rejectValue("yearOfFoundation", "error.book.bookCreating.yearOfFoundationError", "Library Foundation year cannot be in future.");
		}

		//For constraint annotations
		javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		List<Book> books = bibliotheca.getBooks();

		for (int i = 0; i < books.size(); i++) {
			Set<ConstraintViolation<Book>> violations = validator.validate(books.get(i));
			final int index = i;
			//For annotations constraints.
			violations.forEach(error -> {
				errors.rejectValue("books[" +index+ "]." + error.getPropertyPath(), "error.book.bookCreatingError", error.getMessage());
			});

			//For custom validations
			if (books.get(i).getDateOfIssue() != null && books.get(i).getDateOfIssue().isAfter(LocalDate.now())) {
				errors.rejectValue("books[" +i+ "].dateOfIssue",  "error.book.bookCreatingError.dateError", "Date of issue cannot be in future.");
			}
		}
	}
}
