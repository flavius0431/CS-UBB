package repository.database;

import domain.OficiuPersoana;
import repository.Repository0;

public interface IOficiuPersoana extends Repository0<Long, OficiuPersoana> {

    OficiuPersoana Login(String user,String password);

}
