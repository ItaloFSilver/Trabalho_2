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

import br.ufjf.dcc025.trabalho2.model.users.Secretary;

public class SecretaryRepository implements Repository<Secretary> {

    private final String path = dirPath + File.separator + "usersData" + File.separator + "secretaryData.json"; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(Secretary secretary) {
        List<Secretary> secretaries = listAll();
        secretaries.add(secretary);
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(secretaries, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void remove(Secretary secretary) {
        List<Secretary> secretaries = listAll();

        for(Secretary s : secretaries) {
            if(s.getId().equals(secretary.getId())) {
                secretaries.remove(s);
                break;
            }
        }
        File file = new File(path);
        
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(secretaries, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Secretary> listAll() {
        File file = new File(path);
        List<Secretary> secretaries;

        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<Secretary>>(){}.getType();    
            secretaries = gson.fromJson(fileReader, typeList);
            return secretaries == null ? new ArrayList<>() : secretaries;

        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}