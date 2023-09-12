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
public class FindUrednik extends AbstractGenericOperation {

    private List<Urednik> urednici;
    private final String searchParameter;

    public FindUrednik(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        urednici = repository.findAll((Urednik) param, searchParameter);
        GetAllAdministrator op = new GetAllAdministrator();
        op.execute(new Administrator());
        List<Administrator> administratori = op.getAdministratori();
        for (Urednik u : urednici) 
            for (Administrator a : administratori)
                if (u.getAdministrator().getId() == a.getId())
                    u.setAdministrator(a);
    }

    public List<Urednik> getUrednici() {
        return urednici;
    }
    
}
