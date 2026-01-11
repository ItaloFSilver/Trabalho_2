package br.ufjf.dcc025.trabalho2.model.repository;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.User;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MedicRepository extends BaseRepository<Medic> {

    public MedicRepository() {
        super("medicData.json", Medic.class);
    }
}
