package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Role;
import com.st2i.sesame.repository.RoleRepository;
import com.st2i.sesame.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired RoleRepository roleRepo;
	@Override
	public void addRole(Role role) {
		roleRepo.save(role);
	}

	@Override
	public Role findOne(Integer id) {
		return roleRepo.findById(id).get();
	}

	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		roleRepo.deleteById(id);
	}
	

}
