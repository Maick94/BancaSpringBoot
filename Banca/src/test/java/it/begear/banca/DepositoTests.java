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
import it.begear.banca.entity.Deposito;
import it.begear.banca.repository.ContoRepository;
import it.begear.banca.repository.DepositoRepository;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class DepositoTests {

	@Autowired
	private DepositoRepository repoD;

	@Autowired
	private ContoRepository repoC;

	@Test
	void saveTest() {
		Conto conto = repoC.save(new Conto());
		Deposito deposito = new Deposito(100, "01/01/2001", 0, conto);
		Deposito salvato = repoD.save(deposito);
		Assert.assertNotNull(salvato);
	}

	@Test
	void getTest() {
		Deposito deposito = repoD.getOne(1);
		Deposito id = repoD.getOne(deposito.getIdDeposito());
		Assert.assertEquals(deposito.getData(),id.getData());
	}

	@Test
	void deleteTest() {
		Integer id = 1;
		boolean isExistsBeforeDelete = repoD.findById(id).isPresent();
		repoD.deleteById(id);
		boolean isExistsAfterDelete = repoD.findById(id).isPresent();
		assertTrue(isExistsBeforeDelete);
		assertFalse(isExistsAfterDelete);
	}

	@Test
	void getListTest() {
		List<Deposito> listaAttuale = repoD.findAll();
		List<Deposito> listaAttesa = repoD.findAll();
		Assert.assertEquals(listaAttuale, listaAttesa);
	}

}
