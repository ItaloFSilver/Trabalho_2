/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.services;

public enum DayOfWeek {
        SUNDAY("Domingo"),
        MONDAY("Segunda-feira"),
        TUESDAY("Terca-feira"),
        WEDNESDAY("Quarta-feira"),
        THURSDAY("Quinta-feira"),
        FRIDAY("Sexta-feira"),
        SATURDAY("Sabado");

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
                    return WEDNESDAY;
                }
                case "Quinta-feira" -> {
                    return THURSDAY;
                }
                case "Sexta-feira" -> {
                    return FRIDAY;
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
