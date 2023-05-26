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

            Odontologo Richard = new Odontologo(1, "Richard", "Gonzalez");
            Odontologo Marisol = new Odontologo(1, "Marisol", "Lopez");
            Odontologo Luciana = new Odontologo(1, "Luciana", "Benitez");


            OdontologoDaoH2 odontologoDao = new OdontologoDaoH2();

            odontologoDao.guardar(Richard);
            odontologoDao.guardar(Marisol);
            odontologoDao.guardar(Luciana);

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