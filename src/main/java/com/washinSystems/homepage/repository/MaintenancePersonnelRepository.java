package com.washinSystems.homepage.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.washinSystems.homepage.domain.MaintenancePersonnel;

public interface MaintenancePersonnelRepository extends JpaRepository<MaintenancePersonnel, Integer> {
	Page<MaintenancePersonnel> findAll(Pageable pageable);

	@Query("SELECT x FROM MaintenancePersonnel x WHERE x.userName = :UserName")
	List<MaintenancePersonnel> findMaintenancePersonnel(@Param("UserName")String username);
			
}
