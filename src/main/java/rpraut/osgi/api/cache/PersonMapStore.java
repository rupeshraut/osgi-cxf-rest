package rpraut.osgi.api.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hazelcast.core.MapStore;

import rpraut.osgi.api.entity.Person;

/**
 * The Class PersonMapStore.
 */
public class PersonMapStore implements MapStore<Integer, Person> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(PersonMapStore.class.getName());
	
	/** The map store. */
	private AtomicReference<Map<Integer, Person>> mapStore = new AtomicReference<Map<Integer, Person>>(new ConcurrentHashMap<>());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapLoader#load(java.lang.Object)
	 */
	@Override
	public Person load(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapLoader#loadAll(java.util.Collection)
	 */
	@Override
	public Map<Integer, Person> loadAll(Collection<Integer> keys) {
		final Map<Integer, Person> map = new HashMap<>();
		for (Entry<Integer, Person> entry : mapStore.get().entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		} // for
		
		LOGGER.log(Level.INFO, "loading all - {0}", map.size());
		
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapLoader#loadAllKeys()
	 */
	@Override
	public Iterable<Integer> loadAllKeys() {
		LOGGER.log(Level.INFO, "loading all keys - {0}", mapStore.get().size());
		return mapStore.get().keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapStore#store(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void store(Integer key, Person value) {
		LOGGER.log(Level.INFO, "storing - {0}", value);
		mapStore.get().put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapStore#storeAll(java.util.Map)
	 */
	@Override
	public void storeAll(Map<Integer, Person> map) {
		LOGGER.log(Level.INFO, "storing all - {0}", map);
		mapStore.get().putAll(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapStore#delete(java.lang.Object)
	 */
	@Override
	public void delete(Integer key) {
		LOGGER.log(Level.INFO, "deleting - {0}", key);
		mapStore.get().remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hazelcast.core.MapStore#deleteAll(java.util.Collection)
	 */
	@Override
	public void deleteAll(Collection<Integer> keys) {
		LOGGER.log(Level.INFO, "deleting all - {0}", keys);
		for (Integer key : keys) {
			mapStore.get().remove(key);
		} // for
	}
}
