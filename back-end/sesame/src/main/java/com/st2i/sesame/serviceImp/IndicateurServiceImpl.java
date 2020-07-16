package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Indicateur;
import com.st2i.sesame.repository.IndicateurRepository;
import com.st2i.sesame.service.IndicateurService;

@Service
public class IndicateurServiceImpl implements IndicateurService{
	
	@Autowired IndicateurRepository IndicateurRepo;
	@Override
	public void addIndicateur(Indicateur Indicateur) {
		IndicateurRepo.save(Indicateur);
	}

	@Override
	public Indicateur findOne(Integer id) {
		return IndicateurRepo.findById(id).get();
	}

	@Override
	public List<Indicateur> getAll() {
		return IndicateurRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		Indicateur indicateur = IndicateurRepo.findById(id).get();
		indicateur.setNature(null);
		indicateur.setType(null);
		indicateur.setResponsable(null);
		IndicateurRepo.delete(indicateur);
	}

}
