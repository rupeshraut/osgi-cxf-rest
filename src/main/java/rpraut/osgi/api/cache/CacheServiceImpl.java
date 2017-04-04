/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.cache;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.ServiceScope;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import rpraut.osgi.api.entity.Person;
import rpraut.osgi.api.entity.PersonPortableFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CacheServiceImpl.
 *
 * @author rupesh
 */
@Component(scope = ServiceScope.PROTOTYPE, service = CacheService.class)
public class CacheServiceImpl implements CacheService {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(CacheServiceImpl.class.getName());

	/** The hazelcast client. */
	private HazelcastInstance hazelcastClient;

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
	@Modified
	public void modify(Map<String, Object> config) {
		LOG.log(Level.INFO, "starting hezalcast client  instance");
		final ClientConfig clientConfig = new ClientConfig();
		SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
		serializationConfig.addPortableFactory(Person.FACTORY_ID, new PersonPortableFactory());
		serializationConfig.setPortableVersion(1);

		hazelcastClient = HazelcastClient.newHazelcastClient(clientConfig);
		LOG.log(Level.INFO, "hazelcast client instance created...");
	}

	/**
	 * Deactivate.
	 *
	 * @param config
	 *            the config
	 */
	@Deactivate
	public void deactivate(Map<String, Object> config) {
		LOG.log(Level.INFO, "shutting down hazelcast client instance ...");
		hazelcastClient.shutdown();
		LOG.log(Level.INFO, "hazelcast client instance shutdown complete...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rpraut.osgi.api.cache.CacheService#getMap(java.lang.String)
	 */
	public IMap<Integer, Person> getMap(String mapName) {
		return hazelcastClient.getMap(mapName);
	}

}
