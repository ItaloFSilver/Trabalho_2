package br.ufjf.dcc025.trabalho2.model.repository;

import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class PatientRepository extends BaseRepository<Patient> {

    public PatientRepository() {
        super("patientData.json", Patient.class);
    }
}