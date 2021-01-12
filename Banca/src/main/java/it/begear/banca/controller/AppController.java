package it.begear.banca.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.begear.banca.entity.Azienda;
import it.begear.banca.entity.Conto;
import it.begear.banca.entity.Deposito;
import it.begear.banca.entity.Persona;
import it.begear.banca.entity.Prelievo;
import it.begear.banca.service.AziendaService;
import it.begear.banca.service.ContoService;
import it.begear.banca.service.DepositoService;
import it.begear.banca.service.PersonaService;
import it.begear.banca.service.PrelievoService;

@Controller
public class AppController {
	
	@Autowired
	PersonaService personaService;
	@Autowired
    ContoService contoService;
	@Autowired
    AziendaService aziendaService;
	@Autowired
    DepositoService depositoService;
	@Autowired
    PrelievoService prelievoService;
	
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
	public String saveAzienda(@ModelAttribute("azienda") Azienda azienda) {
        String dataApertura= currentDate();
		Conto conto= new Conto(dataApertura,0);
		contoService.saveConto(conto);
		azienda.setConto(conto);
		aziendaService.saveAzienda(azienda);
		return "redirect:/";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/deposito_persona", method=RequestMethod.GET)
	public String depositoPersona(Model model, @Param("keyword") String keyword) {
		Persona persona= personaService.getPersonaByCF(keyword);
		model.addAttribute("persona", persona);
		model.addAttribute("keyword", keyword);
		return "deposito_conto_persona";
	}
	
	
	
	
	@RequestMapping(value = "/inserimento_deposito_persona", method = RequestMethod.POST)
	public String addDepositoPersona(Model model, @ModelAttribute("persona") Persona persona, @Param("importo") Integer importo){
		 
		  /*questo metodo può essere diviso in sottometodi*/
		  String dataDeposito= currentDate();
		  Conto c=persona.getConto();
		  int importoTotale=c.getSaldo()+importo;
		  
		  Deposito d=new Deposito(importo,dataDeposito,importoTotale,c);
		  
		  depositoService.saveDeposito(d);  //inserisco la tupla deposito
		  c.add(d);
		  contoService.saveConto(c);
		  
		  model.addAttribute("importo", importo);
		  
		  c.setSaldo(importoTotale); //update conto
		  contoService.saveConto(c);
	      return "redirect:/";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/deposito_azienda", method=RequestMethod.GET)
	public String depositoAzienda(Model model, @Param("keyword") String keyword) {
		Azienda azienda= aziendaService.getAziendaByPIva(keyword);
		model.addAttribute("azienda", azienda);
		model.addAttribute("keyword", keyword);
		return "deposito_conto_aziendale";
	}
	
	
	
	
	@RequestMapping(value = "/inserimento_deposito_azienda", method = RequestMethod.POST)
	public String addDepositoAzienda(Model model, @ModelAttribute("azienda") Azienda azienda, @Param("importo") Integer importo){
		 
		  /*questo metodo può essere diviso in sottometodi*/
		  String dataDeposito= currentDate();
		  Conto c=azienda.getConto();
		  int importoTotale=c.getSaldo()+importo;
		  
		  Deposito d=new Deposito(importo,dataDeposito,importoTotale,c);
		  
		  depositoService.saveDeposito(d);  //inserisco la tupla deposito
		  c.add(d);
		  contoService.saveConto(c);
		  
		  model.addAttribute("importo", importo);
		  
		  c.setSaldo(importoTotale); //update conto
		  contoService.saveConto(c);
	      return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/prelievo_persona", method=RequestMethod.GET)
	public String prelievoPersona(Model model, @Param("keyword") String keyword) {
		Persona persona= personaService.getPersonaByCF(keyword);
		Double val=null;
		if (persona!=null) {
			val=contoService.getMedia(persona.getConto().getIdConto());
			val=val*30/100;
		}
		model.addAttribute("val", val);
		model.addAttribute("persona", persona);
		model.addAttribute("keyword", keyword);
		return "prelievo_conto_personale";
	}
	
	
	
	
	@RequestMapping(value = "/inserimento_prelievo_persona", method = RequestMethod.POST)
	public String addPrelievoPersona(Model model, @ModelAttribute("persona") Persona persona, @Param("importo") Integer importo){
		 
		
		
		  String dataPrelievo= currentDate();
		  Conto c=persona.getConto();
		  int importoTotale=c.getSaldo()-importo;
		  
		  Prelievo p=new Prelievo(importo,dataPrelievo,importoTotale,c);
		  
		  prelievoService.savePrelievo(p);  
		  c.add(p);
		  contoService.saveConto(c);
		  
		  model.addAttribute("importo", importo);
		  
		  c.setSaldo(importoTotale); //update conto
		  contoService.saveConto(c);
	      return "redirect:/";
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/prelievo_azienda", method=RequestMethod.GET)
	public String prelievoAzienda(Model model, @Param("keyword") String keyword) {
		Azienda azienda= aziendaService.getAziendaByPIva(keyword);
		model.addAttribute("azienda", azienda);
		model.addAttribute("keyword", keyword);
		return "prelievo_conto_aziendale";
	}
	
	
	
	
	@RequestMapping(value = "/inserimento_prelievo_azienda", method = RequestMethod.POST)
	public String addPrelievoAzienda(Model model, @ModelAttribute("azienda") Azienda azienda, @Param("importo") Integer importo){
		 
		  /*questo metodo può essere diviso in sottometodi*/
		  String dataPrelievo= currentDate();
		  Conto c=azienda.getConto();
		  int importoTotale=c.getSaldo()-importo;
		  
		  Prelievo p=new Prelievo(importo,dataPrelievo,importoTotale,c);
		  
		  prelievoService.savePrelievo(p);  
		  c.add(p);
		  contoService.saveConto(c);
		  
		  model.addAttribute("importo", importo);
		  
		  c.setSaldo(importoTotale); //update conto
		  contoService.saveConto(c);
	      return "redirect:/";
	}
	
	

	@RequestMapping("/lista_persona") public String listPersona(Model model) {
		List<Persona> listPersona = personaService.getAllList();
        model.addAttribute("listPersona", listPersona);
        return "lista_persona";
    }
	
	@RequestMapping("/lista_azienda") public String listAzienda(Model model) {
		List<Azienda> listAzienda = aziendaService.getAllList();
        model.addAttribute("listAzienda", listAzienda);
        return "lista_azienda";
    }
	
	@RequestMapping("/lista_movimenti") public String listMovimenti(Model model) {
		List<Deposito> listDeposito = depositoService.getAllList();
		List<Prelievo> listPrelievo = prelievoService.getAllList();
        model.addAttribute("listDeposito", listDeposito);
        model.addAttribute("listPrelievo", listPrelievo);
        return "lista_movimenti";
    }
	
	

	
	
	
	
	
	public String currentDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String data = dateFormatter.format(new Date());
		return data;
	}
	
	
	
}
