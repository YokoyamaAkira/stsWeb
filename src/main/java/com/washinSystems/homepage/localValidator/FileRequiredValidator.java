package com.washinSystems.homepage.localValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileRequiredValidator implements ConstraintValidator<FileRequired, MultipartFile> {
    @Override
    public void initialize(FileRequired constraint) {}

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile != null
                && !multipartFile.getOriginalFilename().isEmpty();
    }
}