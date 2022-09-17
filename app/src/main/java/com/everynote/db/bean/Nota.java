package com.everynote.db.bean;


public class Nota {

    private int id;
    private String texto;
    private Prioridade prioridade;

    public Nota(int id, String texto, Prioridade prioridade) {
        this.id = id;
        this.texto = texto;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }
}
