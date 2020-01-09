package ubb.scs.map.ir.seminar.studentsmanagement.domain.validators;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;

public class HomeworkValidator implements Validator<Homework> {
    @Override
    public void validate(Homework entity) throws ValidationException {
        String errors = "";

        if (entity.getStartWeek() > entity.getDeadlineWeek())
            errors += "deadline week must be after start week; ";

        // TODO find more conditions

        if (errors.length() > 0)
            throw new ValidationException(errors);
    }
}
