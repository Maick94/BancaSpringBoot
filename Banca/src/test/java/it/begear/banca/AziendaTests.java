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

import it.begear.banca.entity.Azienda;
import it.begear.banca.entity.Conto;
import it.begear.banca.repository.AziendaRepository;
import it.begear.banca.repository.ContoRepository;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class AziendaTests {

	@Autowired
	private AziendaRepository repoA;

	@Autowired
	private ContoRepository repoC;

	@Test
	void saveTest() {
		Conto conto = repoC.save(new Conto());
		Azienda azienda = new Azienda("ABCD1234567", "System Inc.", conto);
		Azienda salvato = repoA.save(azienda);
		Assert.assertNotNull(salvato);
	}

	@Test
	void getTest() {
		Azienda azienda = repoA.getOne(1);
		Azienda id = repoA.getOne(azienda.getIdAzienda());
		Assert.assertEquals(azienda.getRagioneSociale(),id.getRagioneSociale());
	}

	@Test
	void deleteTest() {
		Integer id = 1;
		boolean isExistsBeforeDelete = repoA.findById(id).isPresent();
		repoA.deleteById(id);
		boolean isExistsAfterDelete = repoA.findById(id).isPresent();
		assertTrue(isExistsBeforeDelete);
		assertFalse(isExistsAfterDelete);
	}

	@Test
	void getListTest() {
		List<Azienda> listaAttuale = repoA.findAll();
		List<Azienda> listaAttesa = repoA.findAll();
		Assert.assertEquals(listaAttuale, listaAttesa);
	}

}
