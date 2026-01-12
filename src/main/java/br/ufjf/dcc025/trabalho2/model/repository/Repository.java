/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.repository;

import java.io.File;
import java.util.List;

public interface Repository<T> {
    String dirPath = System.getProperty("user.dir") + File.separator + "data";

    void save(T user);
    void remove(T user);
    List<T> listAll();
}
