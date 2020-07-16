package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.TypeDecoupage;
import com.st2i.sesame.repository.TypeDecoupageRepository;
import com.st2i.sesame.service.TypeDecoupageService;

@Service
public class TypeDecoupageServiceImpl implements TypeDecoupageService {
	
	@Autowired TypeDecoupageRepository typeDecoupageRepository;
	
	@Override
	public void addTypeDecoupage(TypeDecoupage type) {
		typeDecoupageRepository.save(type);	}

	@Override
	public TypeDecoupage findOne(Integer id) {
		
		return typeDecoupageRepository.findById(id).get();
	}

	@Override
	public List<TypeDecoupage> getAll() {
		return typeDecoupageRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		TypeDecoupage type = this.findOne(id);
		typeDecoupageRepository.delete(type);
	}

}
