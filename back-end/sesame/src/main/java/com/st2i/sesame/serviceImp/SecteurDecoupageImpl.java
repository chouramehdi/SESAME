package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.SecteurDecoupage;
import com.st2i.sesame.repository.SecteurDecoupageRepository;
import com.st2i.sesame.service.SecteurDecoupageService;
@Service
public class SecteurDecoupageImpl implements SecteurDecoupageService{
	
	@Autowired SecteurDecoupageRepository secteurDecoupageRepo; 
	
	@Override
	public void addSecteurDecoupage(SecteurDecoupage Secteur) {
		secteurDecoupageRepo.save(Secteur);
	}

	@Override
	public SecteurDecoupage findOne(Integer id) {
		return secteurDecoupageRepo.findById(id).get();
	}

	@Override
	public List<SecteurDecoupage> getAll() {
		return secteurDecoupageRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		SecteurDecoupage type = this.findOne(id);
		secteurDecoupageRepo.delete(type);
	}

}
