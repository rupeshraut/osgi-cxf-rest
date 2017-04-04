/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import com.hazelcast.core.IMap;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import rpraut.osgi.api.cache.CacheService;
import rpraut.osgi.api.entity.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rupesh
 */
@Component(//
        name = "rpraut.osgi.api.rest.PersonService", //
        service = PersonService.class, //
        scope = ServiceScope.PROTOTYPE,//
        property = {//
            "service.exported.interfaces=*",//
            "service.exported.configs=org.apache.cxf.rs",//
            "org.apache.cxf.rs.httpservice.context=/person",//
            "service.exported.intents=jackson", //  
            "cxf.bus.prop.skip.default.json.provider.registration=true"//
        })
public class PersonServiceImpl implements PersonService {

    private static final Logger LOG = Logger.getLogger(PersonServiceImpl.class.getName());

    final AtomicReference<Map<Integer, Person>> personRepo = new AtomicReference<Map<Integer, Person>>(new HashMap<Integer, Person>(1));

    final AtomicInteger personId = new AtomicInteger(1);

    private CacheService cacheService;

    private IMap<Integer, Person> personMap;

    public CacheService getCacheService() {
        return cacheService;
    }

    @Reference(service = CacheService.class, unbind = "unsetCacheService")
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public void unsetCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Activate
    public void activate(Map<String, Object> config) {
        modify(config);
    }

    @Activate
    public void modify(Map<String, Object> config) {
        personMap = getCacheService().getMap("PERSON_MAP");
        LOG.log(Level.INFO,"retrived IMap instance for Person");
    }

    public Person sayHello(Integer id) {
//        if (personRepo.get().containsKey(id)) {
//            return personRepo.get().get(id);
//        }

        if (personMap.containsKey(id)) {
            return personMap.get(id);
        }

        return null;
    }

    public Collection<Person> list() {
        return personMap.values();
        //return personRepo.get().values();
    }

    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(personId.getAndIncrement());
        }
        //personRepo.get().put(person.getId(), person);
        return personMap.put(person.getId(), person);
        //return person;
    }

    public Person update(Person person) {
        personRepo.get().put(person.getId(), person);
        return personMap.put(person.getId(), person);
    }

    public boolean delete(Integer id) {
        //personRepo.get().remove(id);
        personMap.remove(id);
        return personMap.containsKey(id);
    }

}
