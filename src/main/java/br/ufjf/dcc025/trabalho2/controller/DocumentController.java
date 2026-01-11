package br.ufjf.dcc025.trabalho2.controller;

import br.ufjf.dcc025.trabalho2.model.services.MedicalDocument;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentController {
    private List<MedicalDocument> documentos;
    private final String ARQUIVO = "documentos.json";
    private Gson gson;

    public DocumentController() {
        this.documentos = new ArrayList<>();
        // Configura data bonita no JSON
        this.gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy HH:mm").create();
        carregar();
    }

    public void emitirDocumento(MedicalDocument doc) {
        documentos.add(doc);
        salvar();
    }

    // Filtra: Retorna apenas os documentos daquele CPF
    public List<MedicalDocument> buscarPorPaciente(String cpfPaciente) {
        return documentos.stream()
                .filter(d -> d.getPatientCpf().equals(cpfPaciente))
                .collect(Collectors.toList());
    }

    private void salvar() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(documentos, writer);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (FileReader reader = new FileReader(f)) {
            Type listType = new TypeToken<ArrayList<MedicalDocument>>(){}.getType();
            documentos = gson.fromJson(reader, listType);
            if (documentos == null) documentos = new ArrayList<>();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
