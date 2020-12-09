package it.begear.banca.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.begear.banca.entity.Azienda;
import it.begear.banca.entity.Conto;
import it.begear.banca.entity.Persona;
import it.begear.banca.service.AziendaService;
import it.begear.banca.service.ContoService;
import it.begear.banca.service.PersonaService;



@Controller
public class AppController {

	@Autowired
	PersonaService personaService;
	@Autowired
    ContoService contoService;
	@Autowired
    AziendaService aziendaService;
	
	
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@RequestMapping("/new_conto_fisico")
	public String showNewPersona(Model model){
		Persona persona= new Persona();
		model.addAttribute("persona", persona);
		return "nuovo_conto_fisico";
	}
	
	@RequestMapping(value = "/save_persona", method = RequestMethod.POST)
	public String savePersona(@ModelAttribute("persona") Persona persona) {
        String dataApertura= currentDate();
		Conto conto= new Conto(dataApertura,0);
		contoService.saveConto(conto);
		persona.setConto(conto);
		personaService.savePersona(persona);
		return "redirect:/";
	}
	
	@RequestMapping("/new_conto_aziendale")
	public String showNewAzienda(Model model){
		Azienda azienda= new Azienda();
		model.addAttribute("azienda", azienda);
		return "nuovo_conto_aziendale";
	}
	
	@RequestMapping(value = "/save_azienda", method = RequestMethod.POST)
	public String savePersona(@ModelAttribute("azienda") Azienda azienda) {
        String dataApertura= currentDate();
		Conto conto= new Conto(dataApertura,0);
		contoService.saveConto(conto);
		azienda.setConto(conto);
		aziendaService.saveAzienda(azienda);
		return "redirect:/";
	}
	
	
	
	
	/*@RequestMapping("/edit/{id}")
	public ModelAndView showEditNewPersona(@PathVariable(name = "id") String id ) {
		ModelAndView mav= new ModelAndView("edit_persona");
		Persona persona=personaService.getPersona(id);
		mav.addObject("persona", persona);
		return mav;
	}*/
	
	
	/*@RequestMapping("/delete/{id}")
	public String deletePersona(@PathVariable(name = "id") int id) {
		personaService.delete(id);
		return "redirect:/";
	}*/
	
	
	public String currentDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String data = dateFormatter.format(new Date());
		return data;
	}
	
	
	
}
