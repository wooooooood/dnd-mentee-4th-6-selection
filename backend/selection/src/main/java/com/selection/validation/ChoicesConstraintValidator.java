package com.selection.validation;

import com.selection.dto.choice.ChoiceRequest;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChoicesConstraintValidator implements
    ConstraintValidator<ChoicesConstraint, List<ChoiceRequest>> {

    @Override
    public boolean isValid(List<ChoiceRequest> value, ConstraintValidatorContext context) {
        return value.size() == 0 || value.size() == 2;
    }
}
