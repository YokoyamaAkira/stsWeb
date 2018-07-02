package com.washinSystems.homepage.web.maintenance;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.washinSystems.homepage.domain.CodeRole;
import com.washinSystems.homepage.service.CodeRoleService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.CodeRoleForm;

@Controller
@RequestMapping("maintenance/manageSettingValues/code")
public class CodeRoleController {
	
	@Autowired
	CodeRoleService codeRoleService;

	@ModelAttribute
	CodeRoleForm setUpForm() {
		return new CodeRoleForm();
	}

	// in page
	@GetMapping(path = "rolesList")
	String list(Model model) {
		List<CodeRole> codeRoles = codeRoleService.findAll();
		model.addAttribute("codeRoles", codeRoles);
		return StringConstant.Absolute_Path_rolesList;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "new_role")
	String newRole(@Validated CodeRoleForm form, BindingResult result, Model model
			/*, @AuthenticationPrincipal LoginUserDetails userDetails*/) {
		if (result.hasErrors()) {
			return list(model);
		}
		form.setRoleId(null);//新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editRole;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "renew_role")
	String renewRole(@RequestParam Integer id, CodeRoleForm form) {
		if (id > 0) {
			CodeRole codeRole = codeRoleService.findOne(id);
			BeanUtils.copyProperties(codeRole, form);
		}
		form.setRoleId(null);//新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editRole;
	}

	// insert data to DB
	@PostMapping(path = "editRole", params = "create")
	String create(@RequestParam Integer id, @Validated CodeRoleForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return renewRole(id, form);
		}
		CodeRole codeRole = new CodeRole();
		BeanUtils.copyProperties(form, codeRole);
		codeRole.setRoleId(null);
		codeRoleService.create(codeRole, userDetails.getUser());
		return StringConstant.Redirect_Path_rolesList;
	}

	// from listPage to editPage
	@GetMapping(path = "editRole", params = "form")
	String roleForm(@RequestParam Integer id, CodeRoleForm form) {
		CodeRole codeRole = codeRoleService.findOne(id);
		BeanUtils.copyProperties(codeRole, form);
		return StringConstant.Absolute_Path_editRole;
	}

	// update data to DB
	@PostMapping(path = "editRole", params = "edit")
	String edit(@RequestParam Integer id, @Validated CodeRoleForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return roleForm(id, form);
		}
		CodeRole codeRole = new CodeRole();
		BeanUtils.copyProperties(form, codeRole);
		codeRoleService.update(codeRole, userDetails.getUser());
		return StringConstant.Redirect_Path_rolesList;
	}

	// from workPage to listPage
	@PostMapping(path = "editRole", params = StringConstant.Params_goToBack)
	String goToList() {
		return StringConstant.Redirect_Path_rolesList;
	}

	// from workPage to listPage
	@PostMapping(path = "createRole", params = StringConstant.Params_goToBack)
	String goToList2() {
		return StringConstant.Redirect_Path_rolesList;
	}

	// from listPage to PreviousPage
	@PostMapping(path = "rolesList", params = StringConstant.Params_goToBack)
	String goToPrevious() {
		return StringConstant.Redirect_Path_newsOperation;
	}

	//delete data from listPage
	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		codeRoleService.delete(id);
		return StringConstant.Redirect_Path_rolesList;
	}

}
