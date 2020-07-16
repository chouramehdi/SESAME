package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Financement;
import com.st2i.sesame.repository.FinancementRepository;
import com.st2i.sesame.service.FinancementService;
@Service
public class FinancementServiceImpl implements FinancementService{
	
	@Autowired FinancementRepository FinancementRepo; 
	@Override
	public void addFinancement(Financement Financement) {
		FinancementRepo.save(Financement);
	}

	@Override
	public Financement findOne(Integer id) {
		return FinancementRepo.findById(id).get();
	}

	@Override
	public List<Financement> getAll() {
		return FinancementRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		Financement finance = this.findOne(id);
		finance.setAutresFinancement(null);
		finance.setBailleur(null);
		finance.setCategorie(null);
		finance.setDevise(null);
		finance.setType(null);
		finance.setNature(null);
		FinancementRepo.delete(finance);
	}

}
