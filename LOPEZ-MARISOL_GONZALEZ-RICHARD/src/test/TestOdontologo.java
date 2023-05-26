package test;

import com.backend.dao.impl.OdontologoDaoH2;
import entity.Odontologo;
import org.junit.Assert;
import org.junit.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class TestOdontologo {
    private static Connection connection = null;
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2()){

    };

    @Test
    public void testListarOdontologo() {
        List<Odontologo> odontologosTest = odontologoService.listarOdontologos();
        assertTrue(odontologosTest.size() > 0);
    }

}
