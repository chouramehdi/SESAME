package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Role;

public interface RoleService {
    void addRole(Role role);
    
    Role findOne(Integer id);
    
    List<Role> getAll();
    
    void delete(Integer id);
    
}


