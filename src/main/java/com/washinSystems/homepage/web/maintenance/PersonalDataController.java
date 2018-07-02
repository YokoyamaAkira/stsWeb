package com.washinSystems.homepage.web.maintenance;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.washinSystems.homepage.SecurityConfig;
import com.washinSystems.homepage.domain.MaintenancePersonnel;
import com.washinSystems.homepage.service.CodeRoleService;
import com.washinSystems.homepage.service.MaintenancePersonnelService;
import com.washinSystems.homepage.service.UserService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.PersonalForm;
import com.washinSystems.homepage.domain.User;

@Controller
@RequestMapping("maintenance/manageSettingValues/personal")
public class PersonalDataController {
	@Autowired
	MaintenancePersonnelService maintenancePersonnelService;
	@Autowired
	CodeRoleService codeRoleService;
	@Autowired
	UserService userService;
	@Autowired
	SecurityConfig securityConfig;
	
	@ModelAttribute
	PersonalForm setUpForm() {
		return new PersonalForm();
	}

	// in page
	@GetMapping(path = "personalData")
	String list(PersonalForm form, @AuthenticationPrincipal LoginUserDetails userDetails) {
		List<MaintenancePersonnel> persons;
		persons = maintenancePersonnelService.findOne(userDetails.getUser());
		if (persons.isEmpty() || persons.size() != 1) {
			return null;
		}
		BeanUtils.copyProperties(persons.get(0), form);
		form.setRoleName(codeRoleService.findOne(form.getRoleId()).getRoleName());
		return StringConstant.Absolute_Path_personalData;
	}

	// Update my nick name
	@PostMapping(path = "editParam", params = "edit_nick_name")
	String editNicName(PersonalForm form, @AuthenticationPrincipal LoginUserDetails userDetails) {
		MaintenancePersonnel person = new MaintenancePersonnel();
		BeanUtils.copyProperties(form, person);
		maintenancePersonnelService.update(person, userDetails.getUser());
		return StringConstant.Redirect_Path_personalData;
	}

	// Update my password
	@PostMapping(path = "editParam", params = "edit_password")
	String editPassword(PersonalForm form, @AuthenticationPrincipal LoginUserDetails userDetails) {
		String encdePassword = securityConfig.passwordEncoder().encode(form.getPassword());
		User user = new User(userDetails.getUsername(), encdePassword);
		MaintenancePersonnel person = new MaintenancePersonnel();
		BeanUtils.copyProperties(form, person);
		userService.update(user);
		maintenancePersonnelService.update(person, userDetails.getUser());
		form.setPassword("");
		return StringConstant.Redirect_Path_personalData;
	}
	
	// from workPage to listPage
	@PostMapping(path = "editParam", params = StringConstant.Params_goToBack)
	String goToTop() {
		return StringConstant.Redirect_Path_newsOperation;
	}


}
