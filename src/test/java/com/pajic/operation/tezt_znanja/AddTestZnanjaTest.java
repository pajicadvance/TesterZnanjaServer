package com.pajic.operation.tezt_znanja;

import com.pajic.controller.Controller;
import com.pajic.model.TestZnanja;
import com.pajic.model.TipTestaZnanja;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddTestZnanjaTest {

    @Test
    void testExecuteOperation() throws Exception {
        TestZnanja testZnanja = new TestZnanja();
        testZnanja.setId(1001L);
        testZnanja.setNaziv("TestTest");
        testZnanja.setPoeniZaProlaz(10);
        testZnanja.setTipTestaZnanja(new TipTestaZnanja(1L, "Kviz"));
        testZnanja.setListaPitanja(new ArrayList<>());
        Controller.getInstance().zapamtiTestZnanja(testZnanja);
        String searchParameter = " WHERE id=" + testZnanja.getId();
        List<TestZnanja> testovi = Controller.getInstance().nadjiTestoveZnanja(searchParameter);
        boolean containsTestZnanja = !testovi.isEmpty();
        Assertions.assertTrue(containsTestZnanja);
    }
}
