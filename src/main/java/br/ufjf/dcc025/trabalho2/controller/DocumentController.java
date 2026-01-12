package br.ufjf.dcc025.trabalho2.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidCPFException;
import static br.ufjf.dcc025.trabalho2.model.repository.Repository.dirPath;
import br.ufjf.dcc025.trabalho2.model.services.MedicalDocument;

public class DocumentController {
    private List<MedicalDocument> documentos;
    private final String path;
    private Gson gson;

    public DocumentController() {
        this.documentos = new ArrayList<>();
        // Configura data bonita no JSON
        this.path = dirPath + File.separator + "servicesData" + File.separator + "medicalDocumentData.json";
        this.gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy HH:mm").create();
        carregar();
    }

    public void emitirDocumento(MedicalDocument doc) {
        documentos.add(doc);
        salvar();
    }

    // Filtra: Retorna apenas os documentos daquele CPF
    public List<MedicalDocument> buscarPorCPF(CPF cpf) throws InvalidCPFException {
        List<MedicalDocument> filtered =  documentos.stream()
                .filter(d -> d.getPatientCpf().equals(cpf.toString()))
                .collect(Collectors.toList());
        
        if(filtered.isEmpty()) {
            //throw new InvalidCPFException("Usuário com CPF " + cpf.toString() + " não encontrado");
        }

        return filtered;
    }

    private void salvar() {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(documentos, writer);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void carregar() {
        File f = new File(path);
        if (!f.exists()) return;
        try (FileReader reader = new FileReader(f)) {
            Type listType = new TypeToken<ArrayList<MedicalDocument>>(){}.getType();
            documentos = gson.fromJson(reader, listType);
            if (documentos == null) documentos = new ArrayList<>();
        } catch (IOException e) { e.printStackTrace(); }
    }
}