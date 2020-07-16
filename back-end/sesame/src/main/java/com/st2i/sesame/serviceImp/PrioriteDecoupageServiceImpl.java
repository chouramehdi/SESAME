package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.PrioriteDecoupage;
import com.st2i.sesame.repository.PrioriteDecoupageRepository;
import com.st2i.sesame.service.PrioriteDecoupageService;

@Service
public class PrioriteDecoupageServiceImpl implements PrioriteDecoupageService{
	
	@Autowired PrioriteDecoupageRepository prioriteDecoupageRepo;
	
	@Override
	public void addPrioriteDecoupage(PrioriteDecoupage Priorite) {
		prioriteDecoupageRepo.save(Priorite);
	}

	@Override
	public PrioriteDecoupage findOne(Integer id) {
		return prioriteDecoupageRepo.findById(id).get();
	}

	@Override
	public List<PrioriteDecoupage> getAll() {
		return prioriteDecoupageRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		PrioriteDecoupage priorite = this.findOne(id);

		prioriteDecoupageRepo.delete(priorite);		
	}

}
