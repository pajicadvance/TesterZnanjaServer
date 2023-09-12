/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.urednik;

import com.pajic.model.Administrator;
import com.pajic.model.Urednik;
import com.pajic.operation.AbstractGenericOperation;
import com.pajic.operation.administrator.GetAllAdministrator;

import java.util.List;

/**
 *
 * @author pajic
 */
public class GetAllUrednik extends AbstractGenericOperation {
    
    private List<Urednik> urednici;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        urednici = repository.getAll((Urednik) param);
        GetAllAdministrator operation = new GetAllAdministrator();
        operation.execute(new Administrator());
        for (Urednik u : urednici) 
            for (Administrator a : operation.getAdministratori())
                if (u.getAdministrator().getId() == a.getId())
                    u.setAdministrator(a);
    }

    public List<Urednik> getUrednici() {
        return urednici;
    }
    
}
