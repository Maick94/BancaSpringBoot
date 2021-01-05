package it.begear.banca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.banca.entity.Azienda;
import it.begear.banca.entity.Persona;
import it.begear.banca.repository.PersonaRepository;




@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepository; 
	
	public Persona getPersonaByCF(String keyword) {
	       return personaRepository.searchCF(keyword);
    }
	
	public List<Persona> getAllList() {
		return personaRepository.findAll();
	}
	
	public void savePersona(Persona persona) {
		personaRepository.save(persona);
	}
	
	public Persona getPersona(int id) {
		return personaRepository.findById(id).get();
	}
	
	

	public void deletePersona(int id) {
		personaRepository.deleteById(id);
	}
	
	
	
	
}

