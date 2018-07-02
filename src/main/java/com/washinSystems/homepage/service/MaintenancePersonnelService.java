package com.washinSystems.homepage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.washinSystems.homepage.domain.MaintenancePersonnel;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.MaintenancePersonnelRepository;

@Service
@Transactional
public class MaintenancePersonnelService {
	@Autowired
	MaintenancePersonnelRepository maintenancePersonnelRepository;
	
	public List<MaintenancePersonnel> findAll() {
        return maintenancePersonnelRepository.findAll();
    }

    public Page<MaintenancePersonnel> findAll(Pageable pageable) {
        return maintenancePersonnelRepository.findAll(pageable);
    }

    public MaintenancePersonnel findOne(Integer id) {
        return maintenancePersonnelRepository.findOne(id);
    }
    
    public List<MaintenancePersonnel> findOne(User user) {
        return maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    }

    public MaintenancePersonnel create(MaintenancePersonnel maintenancePersonnel, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	Date now = new Date();
    	maintenancePersonnel.setRegisteredPerson(persons.get(0).getId());
    	maintenancePersonnel.setEditor(persons.get(0).getId());
    	maintenancePersonnel.setRegistrationDate(now);
    	maintenancePersonnel.setEditDate(now);
        return maintenancePersonnelRepository.save(maintenancePersonnel);
    }

    public MaintenancePersonnel update(MaintenancePersonnel maintenancePersonnel, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	MaintenancePersonnel maintenancePersonnelOld = maintenancePersonnelRepository.findOne(maintenancePersonnel.getId());
    	Date now = new Date();
    	maintenancePersonnel.setEditor(persons.get(0).getId());
    	maintenancePersonnel.setEditDate(now);
    	maintenancePersonnel.setRegistrationDate(maintenancePersonnelOld.getRegistrationDate());
    	maintenancePersonnel.setRegisteredPerson(maintenancePersonnelOld.getRegisteredPerson());
        return maintenancePersonnelRepository.save(maintenancePersonnel);
    }

    public void delete(Integer id) {
        maintenancePersonnelRepository.delete(id);
    }
}
