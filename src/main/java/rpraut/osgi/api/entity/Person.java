/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.entity;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 *
 * @author rupesh
 */
@XmlRootElement
public class Person implements Portable {

	/** The Constant FACTORY_ID. */
	public static final int FACTORY_ID = 5;

	/** The Constant CLASS_ID. */
	public static final int CLASS_ID = 1;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new person.
	 */
	public Person() {
	}

	/**
	 * Instantiates a new person.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public Person(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
		hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Person other = (Person) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		if (!(this.id != other.id && (this.id == null || !this.id.equals(other.id))))
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", name=" + name + '}';
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.nio.serialization.Portable#getFactoryId()
	 */
	public int getFactoryId() {
		return FACTORY_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.nio.serialization.Portable#getClassId()
	 */
	public int getClassId() {
		return CLASS_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hazelcast.nio.serialization.Portable#writePortable(com.hazelcast.nio.
	 * serialization.PortableWriter)
	 */
	public void writePortable(PortableWriter writer) throws IOException {
		writer.writeInt("id", id);
		writer.writeUTF("name", name);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hazelcast.nio.serialization.Portable#readPortable(com.hazelcast.nio.
	 * serialization.PortableReader)
	 */
	public void readPortable(PortableReader reader) throws IOException {
		id = reader.readInt("id");
		name = reader.readUTF("name");
	}

}
