/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.cache;

import com.hazelcast.core.IMap;

import rpraut.osgi.api.entity.Person;

/**
 *
 * @author rupesh
 */
public interface CacheService {

    IMap<Integer, Person> getMap(String mapName);
    
}
