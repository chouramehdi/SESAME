package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.TypeFinancement;
import com.st2i.sesame.repository.TypeFinancementRepository;
import com.st2i.sesame.service.TypeFinancementService;

@Service
public class TypeFinancementServiceImpl implements TypeFinancementService{
	
	@Autowired TypeFinancementRepository typefinanceRepo;
	@Override
	public void addTypeFinancement(TypeFinancement type) {
		typefinanceRepo.save(type);
	}

	@Override
	public TypeFinancement findOne(Integer id) {
		
		return typefinanceRepo.findById(id).get();
	}

	@Override
	public List<TypeFinancement> getAll() {
		return typefinanceRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		typefinanceRepo.deleteById(id);
	}

	@Override
	public TypeFinancement findByDesignation(String designation) {
		return typefinanceRepo.findByDesignation(designation);
	}

}
