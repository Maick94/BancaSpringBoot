package it.begear.banca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.banca.entity.Prelievo;
import it.begear.banca.repository.PrelievoRepository;




@Service
public class PrelievoService {
	
	@Autowired
	private PrelievoRepository prelievoRepository; 

	public List<Prelievo> getAllList() {
		return prelievoRepository.findAll();
	}
	
	public void savePrelievo(Prelievo prelievo) {
		prelievoRepository.save(prelievo);
	}
	
	public Prelievo getPrelievo(int id) {
		return prelievoRepository.findById(id).get();
	}
	
	
}
