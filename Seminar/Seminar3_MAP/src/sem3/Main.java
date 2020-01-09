package sem3;

import sem3.repository.CrudRepository;
import sem3.validators.ValidationException;
import sem3.validators.Validator;

public class Main {
    public static void main(String[] args) {
        Validator<Student> validator = new Validator<Student>() {
            @Override
            public void validate(Student entity) throws ValidationException {

            }

        };

        CrudRepository<Long, Student> repo = new CrudRepository<Long, Student>();

    }
}
