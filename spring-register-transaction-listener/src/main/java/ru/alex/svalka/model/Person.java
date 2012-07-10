package ru.alex.svalka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "family_name" })})
public class Person {
	@Id
	@GeneratedValue
	Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "family_name")
	private String familyName;

	public Person() {
	}

	public Person(String name, String family) {
		this.firstName = name;
		this.familyName = family;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName
				+ ", familyName=" + familyName + "]";
	}
}
