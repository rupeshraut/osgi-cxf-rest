package rpraut.osgi.api.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating PersonPortable objects.
 */
public class PersonPortableFactory implements PortableFactory {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PersonPortableFactory.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.nio.serialization.PortableFactory#create(int)
	 */
	public Portable create(int id) {
		LOG.log(Level.INFO, "class id {0}", id);
		return new Person();
	}// create()

}