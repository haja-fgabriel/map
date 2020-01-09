
package ubb.scs.map.ir.seminar.studentsmanagement;

import com.sun.tools.javac.util.Pair;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.StudentFileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.services.config.ApplicationContext;

public class ConfigFile {
    public static void main(String[] args) {

        StudentFileRepository studentFileRepository = new StudentFileRepository(new StudentValidator(),
                ApplicationContext.getPROPERTIES().getProperty("data.catalog.students"));

        studentFileRepository.findAll().forEach(System.out::println);


    }
}
