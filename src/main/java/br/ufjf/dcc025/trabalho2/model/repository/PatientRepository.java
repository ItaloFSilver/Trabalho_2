package br.ufjf.dcc025.trabalho2.model.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufjf.dcc025.trabalho2.model.Users.Patient;

public class PatientRepository implements Repository<Patient> {

    private final String path = dirPath + File.separator + "userData" + File.separator + "pacientData.json"; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(Patient pacient) {
        List<Patient> pacients = listAll();
        pacients.add(pacient);
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(pacients, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Patient pacient) {

    }

    @Override
    public List<Patient> listAll() {
        File file = new File(path);
        List<Patient> pacients;

        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<Patient>>(){}.getType();    
            pacients = gson.fromJson(fileReader, typeList);
            return pacients == null ? new ArrayList<>() : pacients;

        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}