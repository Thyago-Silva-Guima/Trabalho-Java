package models;
import java.time.LocalDate;

public class Emprestimo {
    private static int contadorId=1; 
    private boolean emprestimoAtivo;
    private int idEmprestimo;
    private Pessoa usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoRealizada;

    public Emprestimo(Pessoa usuario, Livro livro) {
        this.usuario = usuario;
        this.emprestimoAtivo = true;
        this.livro = livro;
        this.idEmprestimo=contadorId++;
        this.dataEmprestimo=LocalDate.now(); 
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(usuario.limiteDiasEmprestimo());
        this.dataDevolucaoRealizada = null;
    }

    public long calcularDiasEmprestados(){
            LocalDate fim= (dataDevolucaoRealizada != null)
            ? dataDevolucaoRealizada 
            :LocalDate.now();
            return java.time.temporal.ChronoUnit.DAYS.between(dataEmprestimo, fim); 
    }
    
    public long calcularDiasAtraso(){
        LocalDate fim = (dataDevolucaoRealizada !=null)
            ? dataDevolucaoRealizada
            : LocalDate.now();
            long atraso = java.time.temporal.ChronoUnit.DAYS.between(dataPrevistaDevolucao, fim);
            return Math.max(0,atraso);
    }

    public boolean isEmprestimoAtivo() {
        return emprestimoAtivo;
    }

    public void setEmprestimoAtivo(boolean emprestimoAtivo) {
        this.emprestimoAtivo = emprestimoAtivo;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

    public void setDataDevolucaoRealizada(LocalDate dataDevolucaoRealizada) {
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Emprestimo{");
        sb.append("emprestimoAtivo=").append(emprestimoAtivo);
        sb.append(", idEmprestimo=").append(idEmprestimo);
        sb.append(", usuario=").append(usuario);
        sb.append(", livro=").append(livro);
        sb.append(", dataPrevistaDevolucao=").append(dataPrevistaDevolucao);
        sb.append(", dataDevolucaoRealizada=").append(dataDevolucaoRealizada);
        sb.append('}');
        return sb.toString();
    }


        
}
