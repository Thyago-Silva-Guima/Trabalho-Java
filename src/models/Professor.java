package models;

public class Professor extends Pessoa{
    private String departamento;
    public Professor(String email, String endereco, String nome, String telefone,String departamento) {
        super(email, endereco, nome, telefone);
        this.departamento= departamento;
    }

    @Override
    public int limiteDiasEmprestimo() {
       
        return 14;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Professor{");
        sb.append("departamento=").append(departamento);
        sb.append('}');
        return sb.toString();
    }
    
}
