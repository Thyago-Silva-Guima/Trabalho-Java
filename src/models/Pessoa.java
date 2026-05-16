package models;

public abstract class  Pessoa {
    protected String nome;
    protected String email;
    protected String endereco;
    protected String telefone;
    private static int contadorId=1;
    private int idUsuario;

    public Pessoa(String email, String endereco, String nome, String telefone) {
        this.email = email;
        this.endereco = endereco;
        this.nome = nome;
        this.telefone = telefone;
        this.idUsuario=contadorId++;
    }
    public abstract int limiteDiasEmprestimo();
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone+ ", idUsuario=" + idUsuario + "]";
    }

   

}
