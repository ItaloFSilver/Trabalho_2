package br.ufjf.dcc025.trabalho2.model.services;

public enum DayOfWeek {
        MONDAY("Segunda-feira"),
        TUESDAY("Terca-feira"),
        WEDNESDAY("Quarta-feira"),
        THURSDAY("Quinta-feira"),
        FRIDAY("Sexta-feira"),
        SATURDAY("Sabado"),
        SUNDAY("Domingo");

        DayOfWeek(String text) {
            this.text = text;
        }

        private String text;
        private int order;

        @Override
        public String toString() {
            return text;
        }

        public static DayOfWeek fromString(String text) {
            switch(text) {
                case "Segunda-feira" -> {
                    return MONDAY;
                }
                case "Terca-feira" -> {
                    return TUESDAY;
                }
                case "Quarta-feira" -> {
                    return MONDAY;
                }
                case "Quinta-feira" -> {
                    return WEDNESDAY;
                }
                case "Sexta-feira" -> {
                    return THURSDAY;
                }
                case "Sabado" -> {
                    return SATURDAY;
                }
                case "Domingo" -> {
                    return SUNDAY;
                }
                default -> throw new IllegalArgumentException();
            }
        }
    } 
