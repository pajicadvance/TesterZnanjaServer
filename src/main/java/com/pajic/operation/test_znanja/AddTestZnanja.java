/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.test_znanja;

import com.pajic.model.Odgovor;
import com.pajic.model.Pitanje;
import com.pajic.model.TestZnanja;
import com.pajic.operation.AbstractGenericOperation;

/**
 * Predstavlja specificnu operaciju koja dodaje test znanja u bazu podataka.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class AddTestZnanja extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        TestZnanja tz = (TestZnanja) param;
        //System.out.println(tz.toString());
        repository.add(tz);
        for (Pitanje p : tz.getListaPitanja()) {
            //System.out.println(p.toString());
            repository.add(p);
            for (Odgovor o : p.getListaOdgovora()) {
                //System.out.println(o.toString());
                repository.add(o);
            }
        }
    }
    
}
