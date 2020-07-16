package com.st2i.sesame.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.GedFile;
import com.st2i.sesame.repository.GedFileRepository;
import com.st2i.sesame.service.GedFileService;
@Service
public class GedFileImpl implements GedFileService{
	
	@Autowired GedFileRepository gedRepo;
	@Override
	public void addGedFile(GedFile file) {
		gedRepo.save(file);
	}

}
