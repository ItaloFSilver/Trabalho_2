package ufjf.dcc025.trabalho2.UsersService;

import ufjf.dcc025.trabalho2.Users.User;
import java.io.File;
import java.util.List;

public abstract class UserService {
    protected static final String dirPath = System.getProperty("user.dir") + File.separator + "UserData" + File.separator;

    public abstract void saveUser(User newUser);
    protected abstract List<User> listUsers();
}