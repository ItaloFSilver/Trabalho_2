/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.repository;

import br.ufjf.dcc025.trabalho2.model.users.Medic;

public class MedicRepository extends BaseRepository<Medic> {

    public MedicRepository() {
        super("medicData.json", Medic.class);
    }
}
