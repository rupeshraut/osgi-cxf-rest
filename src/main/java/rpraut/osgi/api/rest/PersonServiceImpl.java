/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import com.hazelcast.core.IMap;

import rpraut.osgi.api.cache.CacheService;
import rpraut.osgi.api.entity.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonServiceImpl.
 *
 * @author rupesh
 */
@Component(//
		name = "rpraut.osgi.api.rest.PersonService", //
		service = PersonService.class, //
		scope = ServiceScope.PROTOTYPE, //
		property = { //
				"service.exported.interfaces=rpraut.osgi.api.rest.PersonService",//
				"service.exported.configs=org.apache.cxf.rs",//
				"org.apache.cxf.rs.address=/person", //
				"service.exported.intents=jackson", //
				"cxf.bus.prop.skip.default.json.provider.registration=false"//
		})
public class PersonServiceImpl implements PersonService {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PersonServiceImpl.class.getName());

	/** The person id. */
	final AtomicInteger personId = new AtomicInteger(1);

	/** The cache service. */
	private CacheService cacheService;

	/** The person map. */
	private IMap<Integer, Person> personMap;

	/**
	 * Gets the cache service.
	 *
	 * @return the cache service
	 */
	public CacheService getCacheService() {
		return cacheService;
	}

	/**
	 * Sets the cache service.
	 *
	 * @param cacheService
	 *            the cache service
	 */
	@Reference(service = CacheService.class, unbind = "unsetCacheService")
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * Unset cache service.
	 *
	 * @param cacheService
	 *            the cache service
	 */
	public void unsetCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * Activate.
	 *
	 * @param config
	 *            the config
	 */
	@Activate
	public void activate(Map<String, Object> config) {
		modify(config);
	}

	/**
	 * Modify.
	 *
	 * @param config
	 *            the config
	 */
	@Activate
	public void modify(Map<String, Object> config) {
		personMap = getCacheService().getMap("PERSON_MAP");
		LOG.log(Level.INFO, "retrived IMap instance for Person");
	}

	/**
	 * Gets the.
	 *
	 * @param id
	 *            the id
	 * @return the person
	 * @see rpraut.osgi.api.rest.PersonService#sayHello(java.lang.Integer)
	 */
	@Override
	public Person get(Integer id) {
		if (personMap.containsKey(id)) {
			return personMap.get(id);
		}
		return null;
	}

	/**
	 * List.
	 *
	 * @return the collection
	 * @see rpraut.osgi.api.rest.PersonService#list()
	 */
	@Override
	public Collection<Person> list() {
		return personMap.values();
	}

	/**
	 * Save.
	 *
	 * @param person
	 *            the person
	 * @return the person
	 * @see rpraut.osgi.api.rest.PersonService#save(rpraut.osgi.api.entity.Person)
	 */
	@Override
	public Person save(Person person) {
		if (person.getId() == null) {
			person.setId(personId.getAndIncrement());
			personMap.put(person.getId(), person);
		} // if
		return personMap.get(person.getId());
	}

	/**
	 * Update.
	 *
	 * @param person
	 *            the person
	 * @return the person
	 * @see rpraut.osgi.api.rest.PersonService#update(rpraut.osgi.api.entity.Person)
	 */
	@Override
	public Person update(Person person) {
		if (personMap.containsKey(person.getId())) {
			personMap.replace(person.getId(), person);
		}//if
		return get(person.getId());
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 * @see rpraut.osgi.api.rest.PersonService#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) {
		return (personMap.remove(id) != null);

	}

}
