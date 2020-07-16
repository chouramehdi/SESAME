package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.AutresFinancement;
import com.st2i.sesame.repository.AutresFinancementRepository;
import com.st2i.sesame.service.AutresFinancementService;

@Service 
public class AutresFinancementServiceImpl implements AutresFinancementService{
	
	@Autowired AutresFinancementRepository autresFinancementRepo;
	
	@Override
	public void addAutresFinancement(AutresFinancement autresfinancement) {
		autresFinancementRepo.save(autresfinancement);
		
	}

	@Override
	public AutresFinancement findOne(Integer id) {
		return autresFinancementRepo.findById(id).get();
	}

	@Override
	public List<AutresFinancement> getAll() {
		return autresFinancementRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		autresFinancementRepo.deleteById(id);	
	}

	@Override
	public AutresFinancement findByDesignation(String designation) {
		return autresFinancementRepo.findByDesignation(designation);
	}
	
}
