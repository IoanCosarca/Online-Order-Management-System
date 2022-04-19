package bll.validators;

/**Validator interface for defining all validators
 */
public interface Validator<T> {

	public void validate(T t);
}