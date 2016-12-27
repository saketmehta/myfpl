package club.myfpl.validation;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 6:26 PM
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Autowired
    public ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(Object requestObject) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(requestObject);
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException("Validation error!", constraintViolations);
        }
    }
}
