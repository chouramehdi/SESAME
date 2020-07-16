package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Bailleur;
import com.st2i.sesame.repository.BailleurRepository;
import com.st2i.sesame.service.BailleurService;

@Service
public class BailleurServiceImpl implements BailleurService{
	
	@Autowired BailleurRepository bailleurRepo;
	
	@Override
	public void addBailleur(Bailleur bailleur) {
		bailleurRepo.save(bailleur);
	}

	@Override
	public Bailleur findOne(Integer id) {
		return bailleurRepo.findById(id).get();
	}

	@Override
	public List<Bailleur> getAll() {
		return bailleurRepo.findAll();
	}

	@Override
	public void delete(Integer id) {

		bailleurRepo.deleteById(id);
	}

	@Override
	public Bailleur findByDesignation(String designation) {
		return bailleurRepo.findByDesignation(designation);
	}

}
