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
 *
 * @author pajic
 */
public class DeleteTestZnanja extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        TestZnanja tz = (TestZnanja) param;
        for (Pitanje p : tz.getListaPitanja()) {
            for (Odgovor o : p.getListaOdgovora())
                repository.delete(o);
            repository.delete(p);
        }
        repository.delete(tz);
    }
    
}
