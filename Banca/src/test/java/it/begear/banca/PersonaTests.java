package it.begear.banca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import it.begear.banca.entity.Conto;
import it.begear.banca.entity.Persona;
import it.begear.banca.repository.ContoRepository;
import it.begear.banca.repository.PersonaRepository;



@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class PersonaTests {

	@Autowired
	private PersonaRepository repoP;

	@Autowired
	private ContoRepository repoC;

	@Test
	void saveTest() {
		Conto conto = repoC.save(new Conto());
		Persona persona = new Persona("ABCDEFG123456789","Mario","Rossi","31/12/1970",conto);
		Persona salvato = repoP.save(persona);
		Assert.assertNotNull(salvato);
	}

	@Test
	void getTest() {
		Persona persona = repoP.getOne(1);
		Persona id = repoP.getOne(persona.getIdPersona());
		Assert.assertEquals(persona.getCognome(),id.getCognome());
	}

	@Test
	void deleteTest() {
		Integer id = 1;
		boolean isExistsBeforeDelete = repoP.findById(id).isPresent();
		repoP.deleteById(id);
		boolean isExistsAfterDelete = repoP.findById(id).isPresent();
		assertTrue(isExistsBeforeDelete);
		assertFalse(isExistsAfterDelete);
	}

	@Test
	void getListTest() {
		List<Persona> listaAttuale = repoP.findAll();
		List<Persona> listaAttesa = repoP.findAll();
		Assert.assertEquals(listaAttuale, listaAttesa);
	}

}
