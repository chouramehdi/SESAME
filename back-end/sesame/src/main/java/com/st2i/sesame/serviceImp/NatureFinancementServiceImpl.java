package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.NatureFinancement;
import com.st2i.sesame.repository.NatureFinancementRepository;
import com.st2i.sesame.service.NatureFinancementService;
@Service
public class NatureFinancementServiceImpl implements NatureFinancementService{
	
	@Autowired NatureFinancementRepository NatureFinancementRepo;
	@Override
	public void addNatureFinancement(NatureFinancement NatureFinancement) {
		NatureFinancementRepo.save(NatureFinancement);
	}

	@Override
	public NatureFinancement findOne(Integer id) {
		return NatureFinancementRepo.findById(id).get();
	}

	@Override
	public List<NatureFinancement> getAll() {
		return NatureFinancementRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		NatureFinancementRepo.deleteById(id);
		
	}

	@Override
	public NatureFinancement findByDesignation(String designation) {
		return NatureFinancementRepo.findByDesignation(designation);
	}

}
