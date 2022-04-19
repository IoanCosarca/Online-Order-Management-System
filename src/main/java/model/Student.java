package model;

/** Class that is mapped with the database table with the same name */
public class Student {
	private int id;
	private String name;
	private String address;
	private String email;
	private int age;
	/** Constructor with parameters. */
	public Student(String name, String address, String email, int age) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}
	/** Constructor with no parameters for ResultSet object constructor. */
	public Student() {
		super();
	}
	/** Returns the ID. */
	public int getId() {
		return id;
	}
	/** Sets the ID. */
	public void setId(int id) {
		this.id = id;
	}
	/** Returns the Student Name. */
	public String getName() {
		return name;
	}
	/** Sets the Student Name. */
	public void setName(String name) {
		this.name = name;
	}
	/** Returns the address. */
	public String getAddress() {
		return address;
	}
	/** Sets the address. */
	public void setAddress(String address) {
		this.address = address;
	}
	/** Returns the age. */
	public int getAge() {
		return age;
	}
	/** Sets the age. */
	public void setAge(int age) {
		this.age = age;
	}
	/** Returns the email. */
	public String getEmail() {
		return email;
	}
	/** Sets the email. */
	public void setEmail(String email) {
		this.email = email;
	}
}
