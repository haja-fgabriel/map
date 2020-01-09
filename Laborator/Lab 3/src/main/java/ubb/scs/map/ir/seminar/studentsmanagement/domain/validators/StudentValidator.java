package ubb.scs.map.ir.seminar.studentsmanagement.domain.validators;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;

public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student entity) throws ValidationException {
        String errors = "";

        if (entity.getName().equals(""))
            errors += "Nume invalid ";

        if (entity.getMedia() < 1 || entity.getMedia() > 10)
            errors += "Media invalida";

        if (errors.length() > 0)
            throw new ValidationException(errors);
    }
}
