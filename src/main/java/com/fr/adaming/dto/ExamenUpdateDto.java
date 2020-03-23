package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfert Object pour l'entite Examen, utilise pour la methode PUT.
 * Contient l'identifiant.
 * @author Lea
 * 
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class ExamenUpdateDto {

	
	@Positive
	private int idExam;
	
	@NotBlank
	private String dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private  MatiereUpdateDto matiereExamen;
	
}
