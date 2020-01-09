package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Entity;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.InMemoryRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class XMLRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> implements FileRepository<ID, E> {
    private String filename;
    protected Document document;

    public XMLRepository(Validator<E> validator, String filename) {
        super(validator);
        this.filename = filename;
        if (Files.exists(Path.of(filename)))
            loadData();
    }

    public abstract E parse(Object line);
    public abstract Object unparse(E entity);

    public void loadData() {
        try {
            document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(filename);

            Element root = document.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node element = childNodes.item(i);
                if (element instanceof Element) {
                    super.save(parse(element));
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();

            Element root = document.createElement("students");
            document.appendChild(root);

            super.findAll().forEach(elem -> root.appendChild(
                    (Element)unparse(elem))
            );

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            FileOutputStream stream = new FileOutputStream(filename);
            StreamResult streamResult = new StreamResult(stream);

            transformer.transform(source, streamResult);
            stream.close();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E save(E entity) {
        E e = super.save(entity);
        saveData();
        return e;
    }

    @Override
    public E update(E entity) {
        E e = super.update(entity);
        saveData();
        return e;
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        saveData();
        return e;
    }


}
