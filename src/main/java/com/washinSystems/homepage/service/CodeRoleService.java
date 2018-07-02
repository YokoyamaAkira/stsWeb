package com.washinSystems.homepage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.washinSystems.homepage.domain.CodeRole;
import com.washinSystems.homepage.domain.MaintenancePersonnel;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.CodeRoleRepository;
import com.washinSystems.homepage.repository.MaintenancePersonnelRepository;

@Service
@Transactional
public class CodeRoleService {
	@Autowired
	CodeRoleRepository codeRoleRepository;
	
	@Autowired
	MaintenancePersonnelRepository maintenancePersonnelRepository;

	public List<CodeRole> findAll() {
        return codeRoleRepository.findAll();
    }

    public Page<CodeRole> findAll(Pageable pageable) {
        return codeRoleRepository.findAll(pageable);
    }

    public CodeRole findOne(Integer id) {
        return codeRoleRepository.findOne(id);
    }
    
    public CodeRole create(CodeRole codeRole, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	Date now = new Date();
    	codeRole.setRegisteredPerson(persons.get(0).getId());
    	codeRole.setEditor(persons.get(0).getId());
    	codeRole.setRegistrationDate(now);
    	codeRole.setEditDate(now);
        return codeRoleRepository.save(codeRole);
    }

    public CodeRole update(CodeRole codeRole, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	CodeRole codeRoleOld = codeRoleRepository.findOne(codeRole.getRoleId());
    	Date now = new Date();
    	codeRole.setEditor(persons.get(0).getId());
    	codeRole.setEditDate(now);
    	codeRole.setRegistrationDate(codeRoleOld.getRegistrationDate());
    	codeRole.setRegisteredPerson(codeRoleOld.getRegisteredPerson());
        return codeRoleRepository.save(codeRole);
    }

    public void delete(Integer id) {
        codeRoleRepository.delete(id);
    }
}
