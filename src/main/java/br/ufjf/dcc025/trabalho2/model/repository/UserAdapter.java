package br.ufjf.dcc025.trabalho2.model.repository;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.Secretary;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class UserAdapter implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String type = jsonObject.get("profile").getAsString();
        
        switch (type) {
            case "MEDICO" -> {
                return context.deserialize(json, Medic.class);
            }
            case "PACIENTE" -> {
                return context.deserialize(json, Patient.class);
            }
            case "SECRETARIO" -> {
                return context.deserialize(json, Secretary.class);
            }
            default -> {
                break;
            }
        }

        throw new JsonParseException("Tipo de usuário desconhecido: " + type);
    }
}