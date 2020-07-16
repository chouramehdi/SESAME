package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Devise;
import com.st2i.sesame.repository.DeviseRepository;
import com.st2i.sesame.service.DeviseService;

@Service
public class DeviseServiceImpl implements DeviseService{
	
	@Autowired DeviseRepository deviseRepo;
	
	@Override
	public void addDevise(Devise Devise) {
		deviseRepo.save(Devise);
	}

	@Override
	public Devise findOne(Integer id) {
		return deviseRepo.findById(id).get();
	}

	@Override
	public List<Devise> getAll() {
		return deviseRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		deviseRepo.deleteById(id);
	}

	@Override
	public Devise findByDesignation(String designation) {
		return deviseRepo.findByDesignation(designation);
	}
	
}
