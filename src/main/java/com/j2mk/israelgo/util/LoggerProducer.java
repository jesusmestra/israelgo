/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.util;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author jkelsy
 */
public class LoggerProducer {
    
    @Produces 
    public Logger produceLoger(InjectionPoint	injectionPoint)	{
        return  Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());    
    }
}
