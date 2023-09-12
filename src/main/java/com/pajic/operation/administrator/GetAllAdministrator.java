/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.administrator;

import com.pajic.model.Administrator;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Predstavlja specificnu operaciju koja iz baze podataka izvlaci sve administratore i stavlja ih u listu.
 *
 * Sadrzi listu administratora.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class GetAllAdministrator extends AbstractGenericOperation {
    /**
     * Lista administratora.
     */
    private List<Administrator> administratori;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        administratori = repository.getAll((Administrator) param);
    }

    /**
     * Vraca listu administratora.
     * @return administratori - Lista administratora.
     */
    public List<Administrator> getAdministratori() {
        return administratori;
    }
}
