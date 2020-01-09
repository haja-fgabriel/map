package ubb.scs.map.ir.seminar.studentsmanagement.domain.validators;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;

public class GradeValidator implements Validator<Grade> {
    @Override
    public void validate(Grade entity) throws ValidationException {
        String errors = "";
        if (entity.getNumber() < 1 || entity.getNumber() > 10)
            errors += "Invalid grade number; ";
        if (errors.length() > 0)
            throw new ValidationException(errors);
    }
}
