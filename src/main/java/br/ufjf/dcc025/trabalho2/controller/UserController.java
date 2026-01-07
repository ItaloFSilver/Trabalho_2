package br.ufjf.dcc025.trabalho2.controller;

import java.io.File;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.Users.User;

public abstract class UserController {
    protected static final String dirPath = System.getProperty("user.dir") + File.separator + "UserData" + File.separator;

    public abstract void saveUser(User newUser);
    protected abstract List<User> listUsers();
}