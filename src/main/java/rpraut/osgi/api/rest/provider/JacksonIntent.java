/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest.provider;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.osgi.service.component.annotations.Component;

/**
 *
 * @author rupesh
 */
@Component(//
        property = "org.apache.cxf.dosgi.IntentName=jackson" //
)
public class JacksonIntent implements Callable<List<Object>> {

    @Override
    public List<Object> call() throws Exception {
        return Arrays.asList((Object)new JacksonJaxbJsonProvider());
    }
    
}
