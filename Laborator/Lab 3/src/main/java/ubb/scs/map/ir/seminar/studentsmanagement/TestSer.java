package ubb.scs.map.ir.seminar.studentsmanagement;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.serialized.StudentSerializedRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.services.config.ApplicationContext;

public class TestSer {
    public static void main(String[] args) {
        //https://www.baeldung.com/java-serialization
        StudentSerializedRepository repository=new StudentSerializedRepository(new StudentValidator(),
                ApplicationContext.getPROPERTIES().getProperty("data.catalog.studentsSer"));
//        Student student1=new Student("John",7.5f);
//        student1.setId(1L);
//        repository.save(student1);
//        student1=new Student("Maria",5.6f);
//        student1.setId(3L);
//        repository.save(student1);
        repository.findAll().forEach(System.out::println);


    }
}
