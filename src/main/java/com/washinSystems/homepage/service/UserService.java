package com.washinSystems.homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.washinSystems.homepage.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
}
