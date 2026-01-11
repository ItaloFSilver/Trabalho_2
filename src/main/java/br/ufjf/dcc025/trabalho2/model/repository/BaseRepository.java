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

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class BaseRepository<T extends User> implements Repository<User> {
    private final String path;
    private final Gson gson;
    private Class<T> type;

    public BaseRepository(String filename, Class<T> type) {
        this.path = dirPath + File.separator + "usersData" + File.separator + filename;
        gson = new GsonBuilder()
                    .registerTypeAdapter(User.class, new UserAdapter())
                    .setDateFormat("dd/MM/yyyy HH:mm:ss")
                    .setPrettyPrinting()
                    .create();
        this.type = type;
    }

    @Override
    public void save(User user) {
        if(!(user.getClass().equals(type))) {
            return;
        }
        List<User> users = listAll();
        users.add(user);
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
         List<User> users = listAll();

        for(User u : users) {
            if(u.getCPF().equals(user.getCPF())) {
                users.remove(u);
                break;
            }
        }
        File file = new File(path);
        
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new InvalidRemoveException("Erro ao remover usuario");
        }
    }

    @Override
    public List<User> listAll() {
        File file = new File(path);
        List<User> users; 
        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<User>>(){}.getType();    
            users = gson.fromJson(fileReader, typeList);
            return users == null ? new ArrayList<>() : users;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}