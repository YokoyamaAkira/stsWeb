package com.washinSystems.homepage.web.maintenance;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.washinSystems.homepage.service.NoticeEventService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.UploadForm;

@Controller
@RequestMapping("maintenance/newsOperation")
public class UploadController {
//	@Autowired
//	NoticeEventService noticeEventService;
//
//	@ModelAttribute
//	UploadForm setUpForm() {
//		return new UploadForm();
//	}
//
//	// upload file to server
//	@PostMapping(path = "uploadNewsPage", params = "upload_file")
//	String uploadNewsPage(@Validated UploadForm uploadForm, BindingResult result, Model model,
//			@AuthenticationPrincipal LoginUserDetails userDetails) {
//		if (result.hasErrors()) {
//	        return "sample/upload";
//	    }
//		// ファイルを一時ホルダに保存する
//		// ファイル種類から決まる値をセットする
//        StringBuffer filePath = new StringBuffer("./uploadfile")//この定数はパラメタ指定してそこから持ってくる
//                                        .append(File.separator).append("tmp");   //ファイルパス
//
//        // アップロードファイルを格納するディレクトリを作成する
//        File uploadDir = mkdirs(filePath);
//
//        try {
//            // アップロードファイルを置く
//        	String fileName = new File(uploadForm.getMultipartFile().getOriginalFilename()).getName();
//            File uploadFile =
//                    new File(uploadDir.getPath() + File.separator + fileName);
//            byte[] bytes = uploadForm.getMultipartFile().getBytes();
//            BufferedOutputStream uploadFileStream =
//                    new BufferedOutputStream(new FileOutputStream(uploadFile));
//            uploadFileStream.write(bytes);
//            uploadFileStream.close();
//        } catch (Exception e) {
//            // 異常終了時の処理
//        } catch (Throwable t) {
//            // 異常終了時の処理
//        }
//        
//		return StringConstant.Redirect_Path_newsOperation;
//	}
//	
//    /**
//     * アップロードファイルを格納するディレクトリを作成する
//     *
//     * @param filePath
//     * @return
//     */
//    private File mkdirs(StringBuffer filePath){
//        Date now = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        File uploadDir = new File(filePath.toString(), sdf.format(now));
//        // 既に存在する場合はプレフィックスをつける
//        int prefix = 0;
//        while(uploadDir.exists()){
//            prefix++;
//            uploadDir =
//                    new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
//        }
//
//        // フォルダ作成
//        uploadDir.mkdirs();
//
//        return uploadDir;
//    }
//
//	// from workPage to listPage
//	@PostMapping(path = "uploadNewsPage", params = StringConstant.Params_goToBack)
//	String goToTop() {
//		return StringConstant.Redirect_Path_newsOperation;
//	}
}
