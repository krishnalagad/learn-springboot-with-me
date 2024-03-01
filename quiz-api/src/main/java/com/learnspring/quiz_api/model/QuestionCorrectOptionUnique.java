package com.learnspring.quiz_api.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.learnspring.quiz_api.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the correctOption value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = QuestionCorrectOptionUnique.QuestionCorrectOptionUniqueValidator.class
)
public @interface QuestionCorrectOptionUnique {

    String message() default "{Exists.question.correctOption}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class QuestionCorrectOptionUniqueValidator implements ConstraintValidator<QuestionCorrectOptionUnique, String> {

        private final QuestionService questionService;
        private final HttpServletRequest request;

        public QuestionCorrectOptionUniqueValidator(final QuestionService questionService,
                final HttpServletRequest request) {
            this.questionService = questionService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables = 
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(questionService.get(Long.parseLong(currentId)).getCorrectOption())) {
                // value hasn't changed
                return true;
            }
            return !questionService.correctOptionExists(value);
        }

    }

}
