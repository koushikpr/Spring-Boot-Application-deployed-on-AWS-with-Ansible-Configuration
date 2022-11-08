package payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Employee {

	private @Id @GeneratedValue Long id;
	private String name;
	private String contact;

	Employee() {}

	Employee(String name, String contact {

		this.name = name;
		this.contact = contact;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getContact() {
		return this.contact;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Patient))
			return false;
		Patient patient = (Patient) o;
		return Objects.equals(this.id, patient.id) && Objects.equals(this.name, patient.name)
				&& Objects.equals(this.contact, patient.contact);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.contact);
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", Contact details='" + this.contact + '\'' + '}';
	}
}
