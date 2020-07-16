package com.st2i.sesame.entities.pojo;

import java.util.Set;

public class UserPojo {
	private Long id;
	private String username;
	private String password;
	private Set<Integer> rolesId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Integer> getRolesId() {
		return rolesId;
	}

	public void setRolesId(Set<Integer> rolesId) {
		this.rolesId = rolesId;
	}
}
