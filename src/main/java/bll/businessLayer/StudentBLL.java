package bll.businessLayer;

import bll.validators.EmailValidator;
import bll.validators.StudentAgeValidator;
import bll.validators.Validator;
import dataAccessLayer.dao.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**Student class logic. Defines the validators and describes what operations on the database will be performed for the Student table.*/
public class StudentBLL {

	private final List<Validator<Student>> validators;
	private final StudentDAO studentDAO;
	/** Initializes the validators and creates the link with the database (queries, connection). */
	public StudentBLL()
	{
		validators = new ArrayList<Validator<Student>>();
		validators.add(new EmailValidator());
		validators.add(new StudentAgeValidator());

		studentDAO = new StudentDAO();
	}
	/** Receives and adds a student to the database, if it is valid. */
	public void addStudent(Student student)
	{
		for (Validator<Student> v : validators) {
			v.validate(student);
		}
		int id = studentDAO.insert(student);
		if (id != -1) {
			student.setId(id);
		}
	}
	/** Receives a student object and tries to update its data in the database, if the student exists. */
	public void updateStudent(Student student)
	{
		for (Validator<Student> v : validators) {
			v.validate(student);
		}
		if (studentDAO.findById(student.getId()) == null) {
			throw new IllegalArgumentException("The student with the id " + student.getId() + " doesn't exist");
		}
		studentDAO.update(student);
	}
	/** Receives the name of a student and deletes it if it's in the database. Returns 1 if success. */
	public int deleteStudent(String name)
	{
		studentDAO.delete(name);
		return 1;
	}
	/** Searches the database and returns the student with the given name, if it exists. */
	public Student findStudentByName(String name)
	{
		Student st = studentDAO.findByName(name);
		if (st == null) {
			throw new NoSuchElementException("The student with the name " + name + " was not found!");
		}
		return st;
	}
	/** If the student with the given name was found, puts it in a list and returns it. */
	public List<Student> findName(String name)
	{
		Student st = findStudentByName(name);
		List<Student> student = new ArrayList<>();
		student.add(st);
		return student;
	}
	/** Searches the database and returns the student with the given id, if it exists. */
	public Student findStudentById(int id) {
		Student st = studentDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The student with id =" + id + " was not found!");
		}
		return st;
	}
	/** If the student with the given id was found, puts it in a list and returns it. */
	public List<Student> findID(int id)
	{
		Student st = findStudentById(id);
		List<Student> student = new ArrayList<>();
		student.add(st);
		return student;
	}
	/** Returns all the students currently present in the database. */
	public List<Student> findAllStudents() {
		return studentDAO.findAll();
	}
	/** Receives a list of all the student objects from the database and constructs a matrix with their field data. */
	public Object[][] getData(List<Student> list)
	{
		Object[][] data = new Object[list.size()][Student.class.getDeclaredFields().length];
		for (Student record : list)
		{
			Object[] o = new Object[]{record.getId(), record.getName(), record.getAddress(), record.getEmail(), record.getAge()};
			data[list.indexOf(record)] = o;
		}
		return data;
	}

}