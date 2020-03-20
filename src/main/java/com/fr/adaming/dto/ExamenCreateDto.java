package com.fr.adaming.dto;



import javax.validation.constraints.NotBlank;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class ExamenCreateDto {
	
	@NotBlank
	private String dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private MatiereUpdateDto matiereExamen;


	

}
