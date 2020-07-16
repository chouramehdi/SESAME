package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.ResponsableIndicateur;
import com.st2i.sesame.repository.ResponsableIndicateurRepository;
import com.st2i.sesame.service.ResponsableIndicateurService;
@Service
public class ResponsableIndicateurServiceImpl  implements ResponsableIndicateurService{
	
	@Autowired ResponsableIndicateurRepository ResponsableIndicateurRepo;
	
	@Override
	public void addResponsableIndicateur(ResponsableIndicateur Responsable) {
		ResponsableIndicateurRepo.save(Responsable);
		
	}

	@Override
	public ResponsableIndicateur findOne(Integer id) {
		
		return ResponsableIndicateurRepo.findById(id).get();
	}

	@Override
	public List<ResponsableIndicateur> getAll() {
		return ResponsableIndicateurRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		ResponsableIndicateurRepo.deleteById(id);
	}

}
