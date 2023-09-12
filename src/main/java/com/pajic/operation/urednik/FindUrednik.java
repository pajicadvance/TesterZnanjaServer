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
 * Predstavlja specificnu operaciju koja pretrazuje sve urednike po datom parametru i stavlja ih u listu.
 *
 * Sadrzi listu urednika i parametar za pretragu.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class FindUrednik extends AbstractGenericOperation {

    /**
     * Lista urednika.
     */
    private List<Urednik> urednici;
    /**
     * Parametar za pretragu.
     */
    private final String searchParameter;

    /**
     * Konstruktor koji vraca instancu klase FindUrednik sa prosledjenim parametrom za pretragu.
     * @param searchParameter - Parametar za pretragu.
     *
     * @throws NullPointerException - Ukoliko je parametar za pretragu null.
     */
    public FindUrednik(String searchParameter) {
        if(searchParameter == null)
            throw new NullPointerException("Parametar za pretragu ne sme biti null.");

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

    /**
     * Vraca listu urednika.
     * @return urednici - Lista urednika.
     */
    public List<Urednik> getUrednici() {
        return urednici;
    }
    
}
