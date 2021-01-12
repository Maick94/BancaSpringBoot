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
import it.begear.banca.entity.Prelievo;
import it.begear.banca.repository.ContoRepository;
import it.begear.banca.repository.PrelievoRepository;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class PrelievoTests {

	@Autowired
	private PrelievoRepository repoP;
	
	@Autowired
	private ContoRepository repoC;

	@Test
	void saveTest() {
		Conto conto = repoC.save(new Conto());
		Prelievo prelievo = new Prelievo(100, "01/01/2001", 0, conto);
		Prelievo salvato = repoP.save(prelievo);
		Assert.assertNotNull(salvato);
	}

	@Test
	void getTest() {
		Prelievo prelievo = repoP.getOne(1);
		Prelievo id = repoP.getOne(prelievo.getIdPrelievo());
		Assert.assertEquals(prelievo.getData(),id.getData());

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
		List<Prelievo> listaAttuale = repoP.findAll();
		List<Prelievo> listaAttesa = repoP.findAll();
		Assert.assertEquals(listaAttuale, listaAttesa);
	}

}
