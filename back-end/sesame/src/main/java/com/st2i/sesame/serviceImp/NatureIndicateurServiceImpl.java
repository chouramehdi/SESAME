package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.st2i.sesame.entities.NatureIndicateur;
import com.st2i.sesame.repository.NatureIndicateurRepository;
import com.st2i.sesame.service.NatureIndicateurService;

@Service
public class NatureIndicateurServiceImpl implements NatureIndicateurService{
	@Autowired NatureIndicateurRepository natureIndicateurRepo;
	
	@Override
	public void addNatureIndicateur(NatureIndicateur NatureIndicateur) {
		natureIndicateurRepo.save(NatureIndicateur);
	}

	@Override
	public NatureIndicateur findOne(Integer id) {
		return natureIndicateurRepo.findById(id).get();
	}

	@Override
	public List<NatureIndicateur> getAll() {
		return natureIndicateurRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		NatureIndicateur nature = this.findOne(id);
		natureIndicateurRepo.delete(nature);		
	}
}


