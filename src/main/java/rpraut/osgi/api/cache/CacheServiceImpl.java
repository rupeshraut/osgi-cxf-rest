/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.cache;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.nio.serialization.ClassDefinition;
import com.hazelcast.nio.serialization.ClassDefinitionBuilder;
import org.osgi.service.component.annotations.*;
import rpraut.osgi.api.entity.Person;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rupesh
 */
@Component(scope = ServiceScope.PROTOTYPE, service = CacheService.class)
public class CacheServiceImpl implements CacheService {

    private static final Logger LOG = Logger.getLogger(CacheServiceImpl.class.getName());

    private HazelcastInstance hazelcastClient;

    @Activate
    public void activate(Map<String, Object> config) {
        modify(config);
    }

    @Modified
    public void modify(Map<String, Object> config) {
        LOG.log(Level.INFO, "starting hezalcast client  instance");
        final Config clientConfig = new Config();
        clientConfig.getSerializationConfig().addPortableFactory(Person.FACTORY_ID, new Person.PersonPortableFactory());

        final ClassDefinition classDefinition = new ClassDefinitionBuilder(Person.FACTORY_ID, Person.CLASS_ID, 1)
                .addUTFField("person").build();
        clientConfig.getSerializationConfig().addClassDefinition(classDefinition);

        hazelcastClient = HazelcastClient.newHazelcastClient();
        LOG.log(Level.INFO, "hazelcast client instance created...");
    }

    @Deactivate
    public void deactivate(Map<String, Object> config) {
        LOG.log(Level.INFO, "shutting down hazelcast client instance ...");
        hazelcastClient.shutdown();
        LOG.log(Level.INFO, "hazelcast client instance shutdown complete...");
    }

    public IMap<?, ?> getMap(String mapName) {
        return hazelcastClient.getMap(mapName);
    }

}
