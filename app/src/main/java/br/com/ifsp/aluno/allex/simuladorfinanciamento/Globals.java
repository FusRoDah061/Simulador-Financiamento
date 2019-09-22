package br.com.ifsp.aluno.allex.simuladorfinanciamento;

public class Globals {

    private static Globals instance = null;

    private String nomeUsuario;

    public static Globals getInstance() {
        if(instance == null) {
            instance = new Globals();
        }

        return instance;
    }

    private Globals (){}

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
