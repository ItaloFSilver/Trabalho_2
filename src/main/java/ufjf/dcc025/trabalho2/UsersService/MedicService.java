package ufjf.dcc025.trabalho2.UsersService;

import ufjf.dcc025.trabalho2.Users.User;
import ufjf.dcc025.trabalho2.Users.Medic;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MedicService extends UserService {

    private static final String path = UserService.dirPath + "medicData.json";
    private static final File file = new File(path); 
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void saveUser(User newUser) {
        try {
            List<User> medics = listUsers();
            medics.add(newUser);
            mapper.writeValue(file, medics);
        }
        catch(IOException e) {
            System.out.println("Impossivel salvar usuario.");
            return;
        }
    }

    @Override
    protected List<User> listUsers() {
        if(!file.exists() || file.length() == 0)
            return new ArrayList<>();

        try{
            return mapper.readValue(file, new TypeReference<List<User>>() {}); 
        }
        catch(IOException e) {
            return new ArrayList<>();
        }
    }
}
