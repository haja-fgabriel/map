package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.serialized;


import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.serialized.SerializedRepository;

public class StudentSerializedRepository extends SerializedRepository<Long, Student> {

    public StudentSerializedRepository(Validator<Student> validator, String fileName) {
        super(validator, fileName);
    }


}
