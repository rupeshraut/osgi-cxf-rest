/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.cache;

import com.hazelcast.core.IMap;

/**
 *
 * @author rupesh
 */
public interface CacheService<I,T> {

    IMap<?,?> getMap(String mapName);
    
}
