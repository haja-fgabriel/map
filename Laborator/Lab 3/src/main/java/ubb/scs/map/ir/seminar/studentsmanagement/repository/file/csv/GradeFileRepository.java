package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv;

import com.sun.tools.javac.util.Pair;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.AbstractFileRepository;

import java.time.LocalDateTime;

public class GradeFileRepository extends AbstractFileRepository<Pair<Long, Long>, Grade> {
    public GradeFileRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Grade parse(Object entity) {
        String line = (String) entity;
        String[] values = line.split(";");
        Grade grade = new Grade(
                Long.parseLong(values[0].strip()),
                Long.parseLong(values[1].strip()),
                LocalDateTime.parse(values[2].strip()),
                Float.parseFloat(values[3].strip()));
        return grade;
    }

    @Override
    public Object unparse(Grade entity) {
        return entity.getHomeworkID()
                + "; " + entity.getStudentID()
                + "; " + entity.getDate()
                + "; " + entity.getNumber();
    }
}
