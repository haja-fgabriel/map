package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml;

import org.w3c.dom.Element;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.XMLRepository;

public class HomeworkXMLRepository extends XMLRepository<Long, Homework> {
    public HomeworkXMLRepository(Validator<Homework> validator, String filename) {
        super(validator, filename);
    }

    @Override
    public Homework parse(Object line) {
        Element element = (Element) line;
        Long id = Long.parseLong(element.getAttribute("id"));

        String description = element.getElementsByTagName("description")
                .item(0)
                .getTextContent();

        int startWeek = Integer.parseInt(element
                .getElementsByTagName("startWeek")
                .item(0)
                .getTextContent());

        int deadlineWeek = Integer.parseInt(element
                .getElementsByTagName("deadlineWeek")
                .item(0)
                .getTextContent());
        Homework homework = new Homework(description, startWeek, deadlineWeek);
        homework.setId(id);
        return homework;
    }

    @Override
    public Object unparse(Homework entity) {
        Element element = super.document.createElement("homework");
        element.setAttribute("id", Long.toString(entity.getId()));

        Element description = super.document.createElement("description");
        description.setTextContent(entity.getDescription());

        Element startWeek = super.document.createElement("startWeek");
        startWeek.setTextContent(Integer.toString(entity.getStartWeek()));

        Element deadlineWeek = super.document.createElement("deadlineWeek");
        deadlineWeek.setTextContent(Integer.toString(entity.getDeadlineWeek()));

        element.appendChild(description);
        element.appendChild(startWeek);
        element.appendChild(deadlineWeek);
        return element;
    }

}
