package com.washinSystems.homepage.web.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.washinSystems.homepage.SecurityConfig;
import com.washinSystems.homepage.domain.CodeRole;
import com.washinSystems.homepage.domain.MaintenancePersonnel;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.service.CodeRoleService;
import com.washinSystems.homepage.service.MaintenancePersonnelService;
import com.washinSystems.homepage.service.UserService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.PersonalForm;
import com.washinSystems.homepage.web.maintenance.formData.SelectFormForRole;

@Controller
@RequestMapping("maintenance/manageSettingValues/member")
public class MaintenancePersonnelController {
	@Autowired
	CodeRoleService codeRoleService;

	@Autowired
	MaintenancePersonnelService maintenancePersonnelService;

	@Autowired
	UserService userService;

	@Autowired
	SecurityConfig securityConfig;

	/*
	 * @ModelAttribute PersonalForm setUpForm() { return new PersonalForm(); }
	 */

	// in page
	@GetMapping(path = "maintenancePersonnelsList")
	String list(Model model) {
		List<MaintenancePersonnel> persons;
		List<PersonalForm> forms = new ArrayList<>();
		persons = maintenancePersonnelService.findAll();
		PersonalForm form;
		for (MaintenancePersonnel person : persons) {
			form = new PersonalForm();
			BeanUtils.copyProperties(person, form);
			form.setRoleName(codeRoleService.findOne(form.getRoleId()).getRoleName());
			forms.add(form);
		}

		model.addAttribute("maintenancePersonnels", forms);
		return StringConstant.Absolute_Path_maintenancePersonnelsList;
	}

	// from listPage to createPage
	@GetMapping(path = "create") // , params = "renew_maintenancePersonnel")
	String createMaintenancePersonnel(@RequestParam Integer id, PersonalForm form, Model model) {

		tool(id, form, model, StringConstant.Params_create);

		return StringConstant.Absolute_Path_editMaintenancePersonnel;
	}

	// from listPage to editPage
	@GetMapping(path = "editMaintenancePersonnel")
	String editMaintenancePersonnel(@RequestParam Integer id, PersonalForm form, Model model) {

		tool(id, form, model, StringConstant.Params_edit);

		return StringConstant.Absolute_Path_editMaintenancePersonnel;
	}

	void tool(Integer id, PersonalForm form, Model model, String settingCondition) {
		// 既存データで再利用するかしないかの判定。IDが設定で再利用されている。新規の場合は－１が設定されている。
		if (id > 0) {
			// IDよりメンテナンス員の情報を取得し画面フォームに設定
			MaintenancePersonnel person = maintenancePersonnelService.findOne(id);
			BeanUtils.copyProperties(person, form);
			// 選択されたメンテナンス員の役割名を設定
			form.setRoleName(codeRoleService.findOne(form.getRoleId()).getRoleName());
		}
		// 新規作成のためIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		if (settingCondition.equals(StringConstant.Params_create)) {
			form.setId(null);
		}
		// DBより役割データを取得し、画面用のセレクト部品用の役割フォーム作り、モデルへ追加
		List<CodeRole> codeRoles = codeRoleService.findAll();
		SelectFormForRole roleForm;
		List<SelectFormForRole> roleForms = new ArrayList<>();
		for (CodeRole codeRole : codeRoles) {
			roleForm = new SelectFormForRole();
			BeanUtils.copyProperties(codeRole, roleForm);
			roleForms.add(roleForm);
		}
		model.addAttribute("selectRole", roleForms);
	}

	// insert data to DB
	@PostMapping(path = "editMaintenancePersonnel", params = "create")
	String create(@RequestParam Integer id, @Validated PersonalForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
		// 設定判定
		if (form.getUseMailF()) {
			form.setUserName(form.getMailaddress());
		}
		// IDの存在チェック
		if (userService.exists(form.getUserName())) {
			ObjectError arg0 = new ObjectError("userName", form.getUserName() + "のIDが既に存在する");
			result.addError(arg0);
		}
		// IDの文字数チェック
		if (form.getUserName().length() < 4) {
			ObjectError arg0 = new ObjectError("userName", "ユーザ名の文字数が短い。");
			result.addError(arg0);
		}
		// パスワードのチェック
		if (form.getPassword().length() < 7) {
			ObjectError arg0 = new ObjectError("password", "パスワードの文字数が短い。");
			result.addError(arg0);
		}
		if (result.hasErrors()) {
			return createMaintenancePersonnel(id, form, model);
		}

		String encdePassword = securityConfig.passwordEncoder().encode(form.getPassword());
		User user = new User(form.getUserName(), encdePassword);
		form.setPassword("");

		MaintenancePersonnel maintenancePersonnel = new MaintenancePersonnel();
		BeanUtils.copyProperties(form, maintenancePersonnel);
		// 利用フラグがfalseでユーザテーブルのデータを登録
		if (!form.getDelF()) {
			userService.create(user);
		}
		// 新規登録のためNullをセット。参照作成用
		maintenancePersonnel.setId(null);
		maintenancePersonnelService.create(maintenancePersonnel, userDetails.getUser());
		return StringConstant.Redirect_Path_maintenancePersonnelsList;
	}

	// update data to DB
	@PostMapping(path = "editMaintenancePersonnel", params = "edit")
	String edit(@RequestParam Integer id, @Validated PersonalForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
		MaintenancePersonnel oldMaintenancePersonnel = maintenancePersonnelService.findOne(id);
		User oldUser = userService.findOne(oldMaintenancePersonnel.getUserName());
		// 設定判定
		if (form.getUseMailF()) {
			form.setUserName(form.getMailaddress());
		}
		// IDの存在チェック
		// if (userService.notExists(form.getUserName())) {
		// ObjectError arg0 = new ObjectError("userName", form.getUserName() +
		// "のIDが存在しない");
		// result.addError(arg0);
		// }
		// IDの文字数チェック
		if (form.getUserName().length() < 4) {
			ObjectError arg0 = new ObjectError("userName", "ユーザ名の文字数が短い。");
			result.addError(arg0);
		}
		// パスワードのチェック
		if (form.getPassword().length() > 1 && form.getPassword().length() < 7) {
			ObjectError arg0 = new ObjectError("password", "パスワードの文字数が短い。");
			result.addError(arg0);
		}
		// 利用停止より復活でパスワードなしをエラーとする
		if (!form.getDelF() && oldMaintenancePersonnel.getDelF() && form.getPassword().length() < 1) {
			ObjectError arg0 = new ObjectError("password", "パスワードの文字数が短い。");
			result.addError(arg0);
		}
		if (result.hasErrors()) {
			return editMaintenancePersonnel(id, form, model);
		}

		String encdePassword;
		// 8文字以上のチェック後に0文字の時にはパスワードは更新しない。
		if (form.getPassword().trim().length() == 0) {
			encdePassword = oldUser.getEncodedPassword();
		} else {
			encdePassword = securityConfig.passwordEncoder().encode(form.getPassword());
		}

		User user = new User(form.getUserName(), encdePassword);
		form.setPassword("");
		// ユーザ名を変更されていた場合、ユーザテーブルの既存を削除する。
		if (!form.getUserName().equals(oldUser.getUsername())) {
			userService.delete(oldUser.getUsername());
		}
		// 利用停止でユーザテーブルのデータを削除。違うなら新規ユーザを登録
		if (form.getDelF()) {
			if (userService.exists(oldUser.getUsername())) {
				userService.delete(oldUser.getUsername());
			}
		} else {
			userService.update(user);
		}

		MaintenancePersonnel maintenancePersonnel = new MaintenancePersonnel();
		BeanUtils.copyProperties(form, maintenancePersonnel);

		maintenancePersonnelService.update(maintenancePersonnel, userDetails.getUser());
		return StringConstant.Redirect_Path_maintenancePersonnelsList;
	}

	// from workPage to PreviousPage
	@PostMapping(path = "editMaintenancePersonnel", params = StringConstant.Params_goToBack)
	String goToPrevious() {
		return StringConstant.Redirect_Path_maintenancePersonnelsList;
	}

	// from listPage to PreviousPage
	@PostMapping(path = "maintenancePersonnelsList", params = StringConstant.Params_goToBack)
	String goToPrevious2() {
		return StringConstant.Redirect_Path_newsOperation;
	}

}
