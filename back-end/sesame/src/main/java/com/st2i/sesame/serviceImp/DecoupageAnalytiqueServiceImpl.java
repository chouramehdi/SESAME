package com.st2i.sesame.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.DecoupageAnalytique;
import com.st2i.sesame.repository.DecoupageAnalytiqueRepository;
import com.st2i.sesame.service.DecoupageAnalytiqueService;

@Service
public class DecoupageAnalytiqueServiceImpl implements DecoupageAnalytiqueService{
	
	@Autowired DecoupageAnalytiqueRepository decoupageRepo;
	
	@Override
	public void addDecoupageAnalytique(DecoupageAnalytique DecoupageAnalytique) {
		decoupageRepo.save(DecoupageAnalytique);
	}

	@Override
	public DecoupageAnalytique findOne(Integer id) {
		return decoupageRepo.findById(id).get();
	}

	@Override
	public List<DecoupageAnalytique> getAll() {
		return decoupageRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		DecoupageAnalytique decoupage = this.findOne(id);
		decoupage.setNature(null);
		decoupage.setPriorite(null);
		decoupage.setType(null);
		decoupage.setSecteur(null);
		decoupageRepo.deleteById(id);
	}

	@Override
	public Set<Integer> getFils(Integer id) {
		List<DecoupageAnalytique> decoupagelist = this.getAll();
		Set<Integer> fils = new HashSet<>();
		decoupagelist.forEach(decoupage ->{
			if (decoupage.getId_parent() == id)
				fils.add(decoupage.getId());
		});
		if(!fils.isEmpty())
			return fils;
		else {
			Set<Integer> d = new HashSet<>();
			return d;
		}
	}
}
	
