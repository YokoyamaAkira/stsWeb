package com.washinSystems.homepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Boolean notExists(String id){
    	User user = userRepository.findOne(id);
    	if(user == null){
    		return true;
    	}
		return false;
    }
    
    public Boolean exists(String id){
    	User user = userRepository.findOne(id);
    	if(user == null){
    		return false;
    	}
		return true;
    }
    
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.delete(id);
    }
}
