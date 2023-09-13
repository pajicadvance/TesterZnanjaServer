package com.pajic.operation.urednik;

import com.pajic.controller.Controller;
import com.pajic.crypto.BCryptManager;
import com.pajic.model.Administrator;
import com.pajic.model.Urednik;
import com.pajic.operation.administrator.GetAllAdministrator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class DeleteUrednikTest {

    @Test
    void testExecuteOperation() throws Exception {
        GetAllAdministrator op1 = new GetAllAdministrator();
        op1.execute(new Administrator());
        List<Administrator> admins = op1.getAdministratori();
        Urednik urednik = new Urednik();
        urednik.setAdministrator(admins.get(0));
        urednik.setId(1000L);
        urednik.setIme("TestIme");
        urednik.setPrezime("TestPrezime");
        urednik.setUsername("testuser");
        urednik.setPassword(BCryptManager.passwordToHash("testpass"));
        urednik.setDatumRodjenja(LocalDate.of(2020, 1, 8));
        Controller.getInstance().zapamtiUrednika(urednik);
        String searchParameter = " WHERE id=" + urednik.getId();
        List<Urednik> urednici = Controller.getInstance().nadjiUrednike(searchParameter);
        boolean containsUrednik = !urednici.isEmpty();
        Assertions.assertTrue(containsUrednik);
        Controller.getInstance().obrisiUrednika(urednik);
        urednici = Controller.getInstance().nadjiUrednike(searchParameter);
        containsUrednik = !urednici.isEmpty();
        Assertions.assertFalse(containsUrednik);
    }
}
