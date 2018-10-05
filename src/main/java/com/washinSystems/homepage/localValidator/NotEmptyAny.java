package com.washinSystems.homepage.localValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

import javax.validation.Payload;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 指定したフィールドのいずれかが空ではないという相関チェックをBean Validationで実装する<br/>
 * https://dev.classmethod.jp/server-side/java/bean-validation-notemptyany-impl/<br/>
 * Spring Boot 入力チェック <br/>
 * 9.3 新規にValidatorを作成する場合(相関チェック) <br/>
 * http://ziqoo.com/wiki/index.php?Spring%20Boot%20%C6%FE%CE%CF%A5%C1%A5%A7%A5%C3%A5%AF
 * @author yoko
 *
 */
@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyAny.NotEmptyAnyValidator.class)
public @interface NotEmptyAny {
	public String message() default "One of {fields} must not be empty";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] fields();

	class NotEmptyAnyValidator implements ConstraintValidator<NotEmptyAny, Object> {
		private String[] fields;
		private String field1;
		private String message;

		@Override
		public void initialize(NotEmptyAny constraintAnnotation) {
			fields = constraintAnnotation.fields();
			this.message = constraintAnnotation.message();
			this.field1 = fields[0];// 指定された最初のフィールドをエラープロパティ名にする。
		}

		@Override
		public boolean isValid(Object bean, ConstraintValidatorContext context) {
			if (bean == null) {
				return true;
			}

			BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
			//指定されたフェールドの有無を確認し、すべて値が存在しないとエラーオブジェクトを作成する。
			if (!(Stream.of(fields).map(beanWrapper::getPropertyValue).filter(v -> v != null).map(Object::toString)
					.anyMatch(v -> !v.isEmpty()))) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(message).addPropertyNode(field1).addConstraintViolation();
				return false;
			}
			return true;
		}
	}

}
