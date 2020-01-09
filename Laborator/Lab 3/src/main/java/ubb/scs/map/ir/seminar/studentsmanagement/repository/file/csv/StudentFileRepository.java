package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv;


import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.AbstractFileRepository;


public class StudentFileRepository extends AbstractFileRepository<Long, Student> {

    public StudentFileRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Student parse(Object entity) {
        String line = (String) entity;
        String[] split = line.split(";");
        Student s = new Student(split[1].strip(), Float.parseFloat(split[2].strip()), split[3].strip());
        s.setId(Long.parseLong(split[0].strip()));
        return s;
    }

    @Override
    public Object unparse(Student student) {
        return student.getId() + "; " + student.getName() + "; " + student.getMedia() + "; " + student.getGroup();
    }
}
