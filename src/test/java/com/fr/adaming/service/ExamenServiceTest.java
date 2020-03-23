package com.fr.adaming.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Type;
/**
 * Class ExamenServiceTest, implementant l'interface IserviceTest, permettant de tester ExamenService
 * @author Lea
 * @author Lucie
 * @author Jeanne-Marie
 *
 */
@SpringBootTest
public class ExamenServiceTest implements IServiceTest {

	@Autowired
	private ExamenService service;

	// METHODE CREATION

	/**
	 * Methode permettant de tester la creation d'un objet
	 * le resultat du test devrait etre un objet
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingValidExamen_shouldReturnExamenWithId() {
		Matiere mat = new Matiere(1, "maths");
		Examen examenInput = new Examen();
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setCoef(2);
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(mat);
		assertEquals(0, examenInput.getId());

		Examen returnedExamen = service.create(examenInput);
		assertNotEquals(0, returnedExamen.getId());

	}

	/**
	 * Methode permettant de tester la creation d'un objet avec une MAtiere non valide
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingExamenWithNotExistingMatiere_shouldReturnNull() {
		Examen examenInput = new Examen();
		LocalDate date = LocalDate.parse("2020-05-21");
		Matiere mat = new Matiere(2, "anglais");

		examenInput.setCoef(2);
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(mat);
		assertNull(service.create(examenInput));
	}

	/**
	 * Methode permettant de tester la creation d'un objet null
	 * le resultat du test devrait etre null
	 */
	@Test
	public void testCreatingNullExamen_shouldReturnNull() {
		Examen exam = null;
		assertNull(service.create(exam));

	}

	/**
	 * Methode permettant de tester la creation d'un objet avec le champ date null
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenNullDate_shouldReturnNull() {
		Examen examenInput = new Examen();

		examenInput.setCoef(2);
		examenInput.setDate(null);
		examenInput.setType(Type.CC);
		assertNull(service.create(examenInput));

	}

	/**
	 * Methode permettant de tester la creation d'un objet champ matiere null
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenNullMatiere_shouldReturnNull() {
		Examen examenInput = new Examen();
		LocalDate date = LocalDate.parse("2020-05-21");

		examenInput.setCoef(2);
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(null);
		assertNull(service.create(examenInput));

	}

	/**
	 * Methode permettant de tester la creation d'un objet champ id déjà utilisé
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingExamenExistingId_shouldReturnNull() {
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		assertNull(service.create(examenInput));

	}

	// METHODE UPDATE

	/**
	 * Methode permettant de tester l'update d'un objet valide
	 * le resultat du test devrait etre positif
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateValidId_shouldTrue() {
		Matiere mat = new Matiere(1, "maths");
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(mat);

		assertTrue(service.update(examenInput));

	}

	/**
	 * Methode permettant de tester l'update d'un objet champ examen null
	 * le resultat du test devrait etre négatif
	 */
	@Test
	public void testUpdateNullExamen_shouldReturnFalse() {
		assertFalse(service.update(null));
	}


	/**
	 * Methode permettant de tester l'update d'un objet champ id invalide
	 * le resultat du test devrait etre négatif
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateInValidId_shouldReturnFalse() {
		Examen examenInput = new Examen();
		examenInput.setId(2);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);

		assertFalse(service.update(examenInput));

	}


	/**
	 * Methode permettant de tester l'update d'un objet champ date invalide
	 * le resultat du test devrait etre négatif
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateInValidDate_shouldReturnFalse() {
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		examenInput.setDate(null);
		examenInput.setType(Type.CC);

		assertFalse(service.update(examenInput));

	}
	

	/**
	 * Methode permettant de tester l'update d'un objet champ matiere invalide
	 * le resultat du test devrait etre négatif
	 */
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateExamenWithInvalidMatiere_shouldReturnFalse() {
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		examenInput.setDate(LocalDate.parse("2020-05-21"));
		examenInput.setType(Type.CC);
		Matiere matiere = new Matiere (2, "francais");
		examenInput.setMatiere(matiere);

		assertFalse(service.update(examenInput));
	
	}
	
	
	// METHODE DELETE
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		boolean retour = service.deleteById(1);
		assertTrue(retour);

	}

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		boolean retour = service.deleteById(2);
		assertFalse(retour);

	}
	
	// METHODE READ ALL

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnList() {
		List<Examen> expectedList = new ArrayList<Examen>();
		expectedList.add(new Examen(1, LocalDate.parse("2020-03-17"), null, 2, null));
		assertEquals(expectedList, service.readAll());

	}

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		assertEquals(0, service.readAll().size());

	}
	
	// METHODE READ BY

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertEquals(new Examen(1, LocalDate.parse("2020-03-17"), null, 2, null), service.readById(1));

	}

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
			assertNull(service.readById(2));

		}
	
	// METHODE EXIST

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
			assertTrue(service.existsById(1));

		}

	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
			assertFalse(service.existsById(2));

		}

}