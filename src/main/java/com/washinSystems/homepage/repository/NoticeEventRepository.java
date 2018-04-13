package com.washinSystems.homepage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.washinSystems.homepage.domain.NoticeEvent;

public interface NoticeEventRepository extends JpaRepository<NoticeEvent, Integer> {
	Page<NoticeEvent> findAll(Pageable pageable);
}
