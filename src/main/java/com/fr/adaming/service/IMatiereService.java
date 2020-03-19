package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.E;

public interface IMatiereService {
	
	public E create(E entity);

	public List<E> readAll();

	public E readById(Integer id);
	
	public E readByNom(String nom);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);
	
	public boolean update(E matiere);
	
	public List<Examen> readExamenByNomMatiere(String nom);

}
