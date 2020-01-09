package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.serialized;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;


public class HomeworkSerializedRepository extends SerializedRepository<Long, Homework> {

    public HomeworkSerializedRepository(Validator validator, String filename) {
        super(validator, filename);
    }

}
