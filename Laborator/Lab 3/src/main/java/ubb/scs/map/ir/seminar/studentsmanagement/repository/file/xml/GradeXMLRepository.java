package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml;

import com.sun.tools.javac.util.Pair;
import org.w3c.dom.Element;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;

import java.time.LocalDateTime;

public class GradeXMLRepository extends XMLRepository<Pair<Long, Long>, Grade> {
    public GradeXMLRepository(Validator<Grade> validator, String filename) {
        super(validator, filename);
    }

    @Override
    public Grade parse(Object line) {
        Element element = (Element) line;
        Long studentID = Long.parseLong(element.getAttribute("sid"));
        Long homeworkID = Long.parseLong(element.getAttribute("hid"));

        Float number = Float.parseFloat(element.getElementsByTagName("number")
                .item(0)
                .getTextContent());

        LocalDateTime date = LocalDateTime.parse(element.getElementsByTagName("date")
                .item(0)
                .getTextContent());
        String feedback = element.getElementsByTagName("feedback")
                .item(0)
                .getTextContent();
        String professor = element.getElementsByTagName("professor")
                .item(0)
                .getTextContent();

        return new Grade(homeworkID, studentID, date, number, feedback, professor);
    }

    @Override
    public Object unparse(Grade entity) {
        Element elem = super.document.createElement("homework");

        Element number = super.document.createElement("number");
        number.setTextContent(Float.toString(entity.getNumber()));

        Element feedback = super.document.createElement("feedback");
        feedback.setTextContent(entity.getFeedback());

        Element date = super.document.createElement("date");
        date.setTextContent(entity.getDate().toString());

        Element professor = super.document.createElement("professor");
        professor.setTextContent(entity.getProfessor());

        elem.setAttribute("sid", Long.toString(entity.getStudentID()));
        elem.setAttribute("hid", Long.toString(entity.getHomeworkID()));
        elem.appendChild(number);
        elem.appendChild(feedback);
        elem.appendChild(date);
        elem.appendChild(professor);

        return elem;
    }
}
