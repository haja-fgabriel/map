package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml;

import com.sun.tools.javac.util.Pair;
import org.w3c.dom.Element;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.GradeDTO;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.Date;

public class GradeDTOXMLRepository extends XMLRepository<Pair<Long, Long>, GradeDTO> {
    public GradeDTOXMLRepository(Validator<GradeDTO> validator, String filename) {
        super(validator, filename);
    }

    @Override
    public GradeDTO parse(Object line) {
        Element element = (Element) line;

        Long studentID = Long.parseLong(element.getAttribute("sid"));
        Long homeworkID = Long.parseLong(element.getAttribute("hid"));

        Float number = Float.parseFloat(element.getElementsByTagName("number")
                .item(0)
                .getTextContent());

        Integer verifiedWeek = Integer.parseInt(element.getElementsByTagName("verifiedWeek")
                .item(0)
                .getTextContent());

        Integer deadlineWeek = Integer.parseInt(element.getElementsByTagName("deadlineWeek")
                .item(0)
                .getTextContent());

        String feedback = element.getElementsByTagName("feedback")
                .item(0)
                .getTextContent();

        return new GradeDTO(homeworkID, studentID, number, verifiedWeek, deadlineWeek, feedback);
    }

    @Override
    public Object unparse(GradeDTO entity) {
        Element elem = super.document.createElement("gradeDTO");

        Element number = super.document.createElement("number");
        number.setTextContent(Float.toString(entity.getValue()));

        Element feedback = super.document.createElement("feedback");
        feedback.setTextContent(entity.getFeedback());

        Element deadlineWeek = super.document.createElement("deadlineWeek");
        deadlineWeek.setTextContent(Integer.toString(entity.getDeadlineWeek()));

        Element verifiedWeek = super.document.createElement("verifiedWeek");
        verifiedWeek.setTextContent(Integer.toString(entity.getVerifiedWeek()));

        elem.setAttribute("sid", Long.toString(entity.getStudentID()));
        elem.setAttribute("hid", Long.toString(entity.getHomeworkID()));
        elem.appendChild(number);
        elem.appendChild(feedback);
        elem.appendChild(verifiedWeek);
        elem.appendChild(deadlineWeek);

        return elem;
    }
}
