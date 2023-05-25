import com.backend.dao.impl.OdontologoDaoH2;
import entity.Odontologo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/Odontologo;INIT=RUNSCRIPT FROM 'odontologo.sql'", "sa", "");

            Odontologo pepito = new Odontologo(1, "pepito", "otracosa");

            OdontologoDaoH2 odontologoDao = new OdontologoDaoH2();

            odontologoDao.guardar(pepito);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}