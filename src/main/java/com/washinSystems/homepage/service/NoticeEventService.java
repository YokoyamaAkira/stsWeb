package com.washinSystems.homepage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.washinSystems.homepage.domain.MaintenancePersonnel;
import com.washinSystems.homepage.domain.NoticeEvent;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.MaintenancePersonnelRepository;
import com.washinSystems.homepage.repository.NoticeEventRepository;

@Service
@Transactional
public class NoticeEventService {
	@Autowired
	NoticeEventRepository noticeEventRepository;
	
	@Autowired
	MaintenancePersonnelRepository maintenancePersonnelRepository;
	
	public List<NoticeEvent> findAll() {
        return noticeEventRepository.findAll();
    }

    public Page<NoticeEvent> findAll(Pageable pageable) {
        return noticeEventRepository.findAll(pageable);
    }

    public NoticeEvent findOne(Integer id) {
        return noticeEventRepository.findOne(id);
    }

    public NoticeEvent create(NoticeEvent noticeEvent, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	Date now = new Date();
    	noticeEvent.setRegisteredPerson(persons.get(0).getId());
    	noticeEvent.setEditor(persons.get(0).getId());
    	noticeEvent.setRegistrationDate(now);
    	noticeEvent.setEditDate(now);
        return noticeEventRepository.save(noticeEvent);
    }

    public NoticeEvent update(NoticeEvent noticeEvent, User user) {
    	List<MaintenancePersonnel> persons;
    	persons = 	maintenancePersonnelRepository.findMaintenancePersonnel(user.getUsername());
    	if(persons.isEmpty() || persons.size() != 1){
    		return null;
    	}
    	NoticeEvent noticeEventOld = noticeEventRepository.findOne(noticeEvent.getId());
    	Date now = new Date();
    	noticeEvent.setEditor(persons.get(0).getId());
    	noticeEvent.setEditDate(now);
    	noticeEvent.setRegistrationDate(noticeEventOld.getRegistrationDate());
    	noticeEvent.setRegisteredPerson(noticeEventOld.getRegisteredPerson());
        return noticeEventRepository.save(noticeEvent);
    }

    public void delete(Integer id) {
        noticeEventRepository.delete(id);
    }
}
