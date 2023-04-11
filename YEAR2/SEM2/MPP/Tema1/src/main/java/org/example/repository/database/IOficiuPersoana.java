package org.example.repository.database;

import org.example.domain.OficiuPersoana;
import org.example.repository.Repository0;

public interface IOficiuPersoana extends Repository0<Long, OficiuPersoana> {

    OficiuPersoana Login(String user,String password);

}
