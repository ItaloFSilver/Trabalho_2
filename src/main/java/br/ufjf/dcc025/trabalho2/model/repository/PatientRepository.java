/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.repository;

import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class PatientRepository extends BaseRepository<Patient> {

    public PatientRepository() {
        super("patientData.json", Patient.class);
    }
}