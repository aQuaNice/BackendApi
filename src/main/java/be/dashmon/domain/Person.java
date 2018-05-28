package be.dashmon.domain;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class Person {
	
	private String validation;
    private String name;
    private String location;
    
    public String getValidation() {
        return validation;
    }
    public void setValidation(String validation) {
        this.validation = validation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }   
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
////	@Column(name="personid")
//	private long personid;
////	@Column(name="firstname")
//	private String firstname;
//	
////	@Column(name="lastname")
//	private String lastname;
//
//	private int age;
//
//	public Person() {
//	}
//
//	public Person(String firstName, String lastName, int age) {
//		this.firstname = firstName;
//		this.lastname = lastName;
//		this.age = age;
//	}
//
//	public long getPersonId() {
//		return personid;
//	}
//
//	public void setPersonId(long studentId) {
//		this.personid = studentId;
//	}
//
//	public String getFirstName() {
//		return firstname;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstname = firstName;
//	}
//
//	public String getLastName() {
//		return lastname;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastname = lastName;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	@Override
//	public String toString() {
//		return "Person [personId=" + personid + ", firstName=" + firstname + ", lastName=" + lastname + ", age=" + age + "]";
//	}

}
