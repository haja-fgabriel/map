package sem3.validators;

public interface Validator<E> {
    public void validate(E entity) throws ValidationException;

}
