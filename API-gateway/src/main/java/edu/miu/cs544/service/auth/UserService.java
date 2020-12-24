package edu.miu.cs544.service.auth;

import java.util.List;

import edu.miu.cs544.domain.User;

public interface UserService {
	User getByUsername(String username);
	List<User> getAll();
}
