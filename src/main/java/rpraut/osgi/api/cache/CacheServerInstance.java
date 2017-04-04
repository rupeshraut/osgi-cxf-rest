/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.cache;


import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
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
@Component(immediate = true, service = CacheServerInstance.class, scope = ServiceScope.SINGLETON)
public class CacheServerInstance {

    private static final Logger LOG = Logger.getLogger(CacheServerInstance.class.getName());

    private HazelcastInstance hazelcastInstance;

    @Activate
    public void activate(Map<String, Object> config) {
        modify(config);
    }

    @Modified
    public void modify(Map<String, Object> config) {
        LOG.log(Level.INFO,"starting HezalcastInstance");
                
        final Config serverConfig = new Config();
        
        serverConfig.getSerializationConfig().addPortableFactory(Person.FACTORY_ID, new Person.PersonPortableFactory());
        
        final ClassDefinition classDefinition = new ClassDefinitionBuilder(Person.FACTORY_ID, Person.CLASS_ID,1)
                .addUTFField("person").build();
        serverConfig.getSerializationConfig().addClassDefinition(classDefinition);
        LOG.log(Level.INFO,"added serialization for Person entity...");
        
        final MapConfig mapConfig = new MapConfig();
        mapConfig.setAsyncBackupCount(1);
        mapConfig.setName("PERSON_MAP");
        serverConfig.addMapConfig(mapConfig);
        LOG.log(Level.INFO,"added PERSON_MAP config...");
        
        hazelcastInstance = Hazelcast.newHazelcastInstance(serverConfig);
        
        LOG.log(Level.INFO,"hazelcast instance created...");
    }

    @Deactivate
    public void deactivate(Map<String, Object> config) {
        LOG.log(Level.INFO,"shutting down hazelcast instance ...");
        hazelcastInstance.shutdown();
        LOG.log(Level.INFO,"hazelcast instance shutdown complete...");
    }

}
