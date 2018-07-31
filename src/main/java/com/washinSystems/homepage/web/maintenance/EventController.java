package com.washinSystems.homepage.web.maintenance;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.washinSystems.homepage.domain.NoticeEvent;
import com.washinSystems.homepage.service.NoticeEventService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.EventForm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("maintenance/newsOperation")
public class EventController {
	@Autowired
	NoticeEventService noticeEventService;

	@ModelAttribute
	EventForm setUpForm() {
		return new EventForm();
	}

	// in page
	@GetMapping
	String list(Model model) {
		List<NoticeEvent> noticeEvents = noticeEventService.findAll();
		model.addAttribute("noticeEvents", noticeEvents);
		return StringConstant.Absolute_Path_eventsList;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "new_edit")
	String newEvent(EventForm form) {
		form.setId(null);// 新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editEvent;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "renew_edit")
	String renewEvent(@RequestParam Integer id, EventForm form) {
		if (id > 0) {
			NoticeEvent noticeEvent = noticeEventService.findOne(id);
			BeanUtils.copyProperties(noticeEvent, form);
		}
		form.setId(null);// 新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editEvent;
	}

	// insert data to DB
	@PostMapping(path = "eventEdit", params = "create")
	String create(@RequestParam Integer id, @Validated EventForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return renewEvent(id, form);
		}
		String fileName = new File(form.getUploadFile().getOriginalFilename()).getName();
		NoticeEvent noticeEvent = new NoticeEvent();
		BeanUtils.copyProperties(form, noticeEvent);
		noticeEvent.setId(null);
		noticeEventService.create(noticeEvent, userDetails.getUser());
		if (form.getUploadF()) {// サブファイルがあるときにアップロードページへ
			uploadNewsPage(form);
		}
		return StringConstant.Redirect_Path_newsOperation;
	}

	// from listPage to editPage
	@GetMapping(path = "eventEdit", params = "form")
	String editForm(@RequestParam Integer id, EventForm form) {
		NoticeEvent noticeEvent = noticeEventService.findOne(id);
		BeanUtils.copyProperties(noticeEvent, form);
		return StringConstant.Absolute_Path_editEvent;
	}

	// update data to DB
	@PostMapping(path = "eventEdit", params = "edit")
	String edit(@RequestParam Integer id, @Validated EventForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		NoticeEvent noticeEvent = new NoticeEvent();
		BeanUtils.copyProperties(form, noticeEvent);
		noticeEventService.update(noticeEvent, userDetails.getUser());
		if (form.getUploadF()) {// サブファイルがあるときにアップロードページへ
			uploadNewsPage(form);
		}
		return StringConstant.Redirect_Path_newsOperation;
	}

	// from workPage to listPage
	@PostMapping(path = "eventEdit", params = StringConstant.Params_goToBack)
	String goToTop() {
		return StringConstant.Redirect_Path_newsOperation;
	}

	// from workPage to listPage
	@PostMapping(path = "createEvent", params = StringConstant.Params_goToBack)
	String goToTop2() {
		return StringConstant.Redirect_Path_newsOperation;
	}

	// delete data from listPage
	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		noticeEventService.delete(id);
		return StringConstant.Redirect_Path_newsOperation;
	}
	
	String uploadNewsPage(EventForm form){
		// ファイルを一時ホルダに保存する
		// ファイル種類から決まる値をセットする
        StringBuffer filePath = new StringBuffer("./uploadfile")//この定数はパラメタ指定してそこから持ってくる
                                        .append(File.separator).append("tmp");   //ファイルパス

        // アップロードファイルを格納するディレクトリを作成する
        File uploadDir = mkdirs(filePath);

        try {
            // アップロードファイルを置く
        	String fileName = new File(form.getUploadFile().getOriginalFilename()).getName();
            File uploadFile =
                    new File(uploadDir.getPath() + File.separator + fileName);
            byte[] bytes = form.getUploadFile().getBytes();
            BufferedOutputStream uploadFileStream =
                    new BufferedOutputStream(new FileOutputStream(uploadFile));
            uploadFileStream.write(bytes);
            uploadFileStream.close();
        } catch (Exception e) {
            // 異常終了時の処理
        } catch (Throwable t) {
            // 異常終了時の処理
        }
		return null;
	}
	
  /**
  * アップロードファイルを格納するディレクトリを作成する
  *
  * @param filePath
  * @return
  */
 private File mkdirs(StringBuffer filePath){
     Date now = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     File uploadDir = new File(filePath.toString(), sdf.format(now));
     // 既に存在する場合はプレフィックスをつける
     int prefix = 0;
     while(uploadDir.exists()){
         prefix++;
         uploadDir =
                 new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
     }

     // フォルダ作成
     uploadDir.mkdirs();

     return uploadDir;
 }
}