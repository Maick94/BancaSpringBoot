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
import it.begear.banca.repository.ContoRepository;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class ContoTests {

	@Autowired
	private ContoRepository repoC;

	@Test
	void saveTest() {
		Conto conto = new Conto("01/01/2001", 0);
		Conto salvato = repoC.save(conto);
		Assert.assertNotNull(salvato);
	}

	@Test
	void getTest() {
		Conto conto = repoC.getOne(1);
		Conto id = repoC.getOne(conto.getIdConto());
		Assert.assertEquals(conto.getDataApertura(),id.getDataApertura());
	}

	@Test
	void deleteTest() {
		Integer id = 1;
		boolean isExistsBeforeDelete = repoC.findById(id).isPresent();
		repoC.deleteById(id);
		boolean isExistsAfterDelete = repoC.findById(id).isPresent();
		assertTrue(isExistsBeforeDelete);
		assertFalse(isExistsAfterDelete);
	}

	@Test
	void getListTest() {
		List<Conto> listaAttuale = repoC.findAll();
		List<Conto> listaAttesa = repoC.findAll();
		Assert.assertEquals(listaAttuale, listaAttesa);
	}

}
