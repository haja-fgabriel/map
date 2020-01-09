package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.AbstractFileRepository;

public class HomeworkFileRepository extends AbstractFileRepository<Long, Homework> {

    public HomeworkFileRepository(Validator<Homework> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Homework parse(Object entity) {
        String line = (String) entity;
        String[] values = line.split(";");
        Homework homework = new Homework(
                values[1].strip(),
                Integer.parseInt(values[2].strip()),
                Integer.parseInt(values[3].strip()));
        homework.setId(Long.parseLong(values[0].strip()));
        return homework;
    }

    @Override
    public Object unparse(Homework homework) {
        return homework.getId() + "; " + homework.getDescription()
                + "; " + homework.getStartWeek() + "; " + homework.getDeadlineWeek();
    }
}
