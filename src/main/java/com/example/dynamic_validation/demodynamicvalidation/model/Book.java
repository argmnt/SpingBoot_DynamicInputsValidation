package com.example.dynamic_validation.demodynamicvalidation.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created on September, 2018
 *
 * @author yagiz
 */
@Data
public class Book implements Serializable {

	@NotBlank
	private String name;

	@NotBlank
	private String writer;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfIssue;

}
