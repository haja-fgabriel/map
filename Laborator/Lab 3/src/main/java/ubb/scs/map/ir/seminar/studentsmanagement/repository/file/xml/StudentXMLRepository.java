package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml;

import org.w3c.dom.Element;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.XMLRepository;

public class StudentXMLRepository extends XMLRepository<Long, Student> {

    public StudentXMLRepository(Validator<Student> validator, String filename) {
        super(validator, filename);
    }

    @Override
    public Student parse(Object entity) {
        Element element = (Element) entity;
        Long id = Long.parseLong(element.getAttribute("id"));
        String name = element.getElementsByTagName("name")
                .item(0)
                .getTextContent();
        float media = Float.parseFloat(element.getElementsByTagName("media")
                .item(0)
                .getTextContent());

        String group = element.getElementsByTagName("group")
                .item(0)
                .getTextContent();

        Student s = new Student(name, media, group);
        s.setId(id);
        return s;
    }

    @Override
    public Object unparse(Student entity) {
        Element elem = super.document.createElement("student");

        Element name = super.document.createElement("name");
        name.setTextContent(entity.getName());

        Element group = super.document.createElement("group");
        group.setTextContent(entity.getGroup());

        Element media = super.document.createElement("media");
        media.setTextContent(Float.toString(entity.getMedia()));

        elem.setAttribute("id", Long.toString(entity.getId()));
        elem.appendChild(name);
        elem.appendChild(media);
        elem.appendChild(group);

        return elem;
    }

}
