package com.librairie.service;

import java.util.Set;

import com.librairie.domain.security.User;
import com.librairie.domain.security.UserRole;

public interface UserService {
	User createUser(User user, Set<UserRole> userRoles);
}
