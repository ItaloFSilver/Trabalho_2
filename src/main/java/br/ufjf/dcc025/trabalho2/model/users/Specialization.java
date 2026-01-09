package br.ufjf.dcc025.trabalho2.model.users;

public enum Specialization {
    PEDIATRA("Pediatra"),
    DERMATOLOGISTA("Dermatologista"),
    CARDIOLOGISTA("Cardiologista"),
    ORTOPEDISTA("Ortopedista");

    private final String text;

    Specialization(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static Specialization fromString(String s) {
        if (s == null) throw new IllegalArgumentException("Tipo nulo");
        switch (s) {
            case "Pediatra" -> {
                return PEDIATRA;
            }
            case "Dermatologista" -> {
                return DERMATOLOGISTA;
            }
            case "Cardiologista" -> {
                return CARDIOLOGISTA;
            }
            case "Ortopedista" -> {
                return ORTOPEDISTA;
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + s);
        }
    }
}
