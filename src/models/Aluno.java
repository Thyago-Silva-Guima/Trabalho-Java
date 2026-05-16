package models;

public class Aluno extends Pessoa {
    private int codigoMatricula;

    public Aluno(String email, String endereco, String nome, String telefone,int codigoMatricula) {
        super(email, endereco, nome, telefone);
        this.codigoMatricula=codigoMatricula;
    }
    
    @Override
    public int limiteDiasEmprestimo(){
        return 7;
    }

    public int getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(int codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    @Override
    public String toString() {
        return "Aluno [codigoMatricula=" + codigoMatricula + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone + ", limiteDiasEmprestimo()=" + limiteDiasEmprestimo() + "]";
    }
}
