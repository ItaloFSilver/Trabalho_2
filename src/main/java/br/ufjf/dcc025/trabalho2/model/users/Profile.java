package br.ufjf.dcc025.trabalho2.model.users;

public enum Profile {
    PACIENTE("Paciente"),
    MEDICO("Medico"),
    SECRETARIO("Secretario");

    private final String label;

    Profile(String label) { 
        this.label = label; 
    }

    @Override 
    public String toString() {
         return label; 
    }

    public static Profile fromString(String s) {
        if (s == null) throw new IllegalArgumentException("Tipo nulo");
        switch (s) {
            case "Paciente" -> {
                return PACIENTE;
            }
            case "Médico" -> {
                return MEDICO;
            }
            case "Secretário" -> {
                return SECRETARIO;
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + s);
        }
    }
}