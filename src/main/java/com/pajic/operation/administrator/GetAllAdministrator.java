/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.administrator;

import com.pajic.model.Administrator;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;


/**
 *
 * @author pajic
 */
public class GetAllAdministrator extends AbstractGenericOperation {
    
    private List<Administrator> administratori;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        administratori = repository.getAll((Administrator) param);
    }

    public List<Administrator> getAdministratori() {
        return administratori;
    }
}
