package com.example.dynamic_validation.demodynamicvalidation.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on September, 2018
 *
 * @author yagiz
 */
@Data
public class Bibliotheca implements Serializable {

	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate yearOfFoundation;

	private List<Book> books = new ArrayList<>();

	public Bibliotheca(Book book) {
		//For first book fields in newBibliotheca page
		books.add(book);
	}

}
