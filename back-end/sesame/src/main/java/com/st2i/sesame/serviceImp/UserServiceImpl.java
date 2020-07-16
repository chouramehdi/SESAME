package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.User;
import com.st2i.sesame.repository.UserRepository;
import com.st2i.sesame.service.UserService;

@Service
public class UserServiceImpl  implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public void addUser(User user) {
		userRepo.save(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}
