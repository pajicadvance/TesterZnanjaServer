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
 * Predstavlja specificnu operaciju koja iz baze podataka izvlaci sve urednike i stavlja ih u listu.
 *
 * Sadrzi listu urednika.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class GetAllUrednik extends AbstractGenericOperation {

    /**
     * Lista urednika.
     */
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

    /**
     * Vraca listu urednika.
     * @return urednici - Lista urednika.
     */
    public List<Urednik> getUrednici() {
        return urednici;
    }
    
}
