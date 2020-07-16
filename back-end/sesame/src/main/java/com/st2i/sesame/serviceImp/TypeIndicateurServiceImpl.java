package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.TypeIndicateur;
import com.st2i.sesame.repository.TypeIndicateurRepository;
import com.st2i.sesame.service.TypeIndicateurService;

@Service
public class TypeIndicateurServiceImpl implements TypeIndicateurService{
	
	@Autowired TypeIndicateurRepository typeIndicateurRepo ;
	@Override
	public void addTypeIndicateur(TypeIndicateur Type) {
		typeIndicateurRepo.save(Type);
	}

	@Override
	public TypeIndicateur findOne(Integer id) {
		return typeIndicateurRepo.findById(id).get();
	}

	@Override
	public List<TypeIndicateur> getAll() {
		return typeIndicateurRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		TypeIndicateur typeIndicateur = this.findOne(id);
		typeIndicateurRepo.delete(typeIndicateur);
	}

}
