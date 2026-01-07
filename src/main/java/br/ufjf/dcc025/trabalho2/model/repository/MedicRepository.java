package br.ufjf.dcc025.trabalho2.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import br.ufjf.dcc025.trabalho2.model.Users.Medic;

public class MedicRepository implements Repository<Medic> {
    private final String path = dirPath + File.separator + "userData" + File.separator + "medicData.json"; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(Medic medic) {
        List<Medic> medics = listAll();
        medics.add(medic);
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(medics, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Medic medic) {
    }

    @Override
    public List<Medic> listAll() {
        File file = new File(path);
        List<Medic> medics; 
        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<Medic>>(){}.getType();    
            medics = gson.fromJson(fileReader, typeList);
            return medics == null ? new ArrayList<>() : medics;

        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
