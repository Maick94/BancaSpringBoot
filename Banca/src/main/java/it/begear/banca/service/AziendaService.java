package it.begear.banca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.banca.entity.Azienda;
import it.begear.banca.repository.AziendaRepository;

@Service
public class AziendaService {
	
	@Autowired
	private AziendaRepository aziendaRepository; 

	
	public List<Azienda> getAllList() {
		return aziendaRepository.findAll();
	}
	
	public void saveAzienda(Azienda azienda) {
		aziendaRepository.save(azienda);
	}
	
	public Azienda getAzienda(int id) {
		return aziendaRepository.findById(id).get();
	}


	public void deleteAzienda(int id) {
		aziendaRepository.deleteById(id);
	}
}


