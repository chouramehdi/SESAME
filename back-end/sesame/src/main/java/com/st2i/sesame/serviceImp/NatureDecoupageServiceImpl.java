package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.NatureDecoupage;
import com.st2i.sesame.repository.NatureDecoupageRepository;
import com.st2i.sesame.service.NatureDecoupageService;

@Service
public class NatureDecoupageServiceImpl implements NatureDecoupageService{
	
	@Autowired NatureDecoupageRepository natureDecoupageRepo;
	
	@Override
	public void addNatureDecoupage(NatureDecoupage NatureDecoupage) {
		natureDecoupageRepo.save(NatureDecoupage);
	}

	@Override
	public NatureDecoupage findOne(Integer id) {
		return natureDecoupageRepo.findById(id).get();
	}

	@Override
	public List<NatureDecoupage> getAll() {
		return natureDecoupageRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		NatureDecoupage nature = this.findOne(id);
		natureDecoupageRepo.delete(nature);
	}

}
