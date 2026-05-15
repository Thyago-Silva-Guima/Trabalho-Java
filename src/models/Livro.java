package models;

public class Livro {

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Livro.contadorId = contadorId;
    }
    private int idLivro;
    private int anoPublicacao;
    private int numeroExemplaresDisponiveis;
    private String autor;
    private String titulo;
    private static int contadorId = 1;
    

    public Livro(int anoPublicacao, String autor, int numeroExemplaresDisponiveis, String titulo) {
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.idLivro = contadorId++;
        this.numeroExemplaresDisponiveis = numeroExemplaresDisponiveis;
        this.titulo = titulo;
    }

    public int getIdLivro(){
        return idLivro;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getNumeroExemplaresDisponiveis() {
        return numeroExemplaresDisponiveis;
    }

    public void setNumeroExemplaresDisponiveis(int numeroExemplaresDisponiveis) {
        this.numeroExemplaresDisponiveis = numeroExemplaresDisponiveis;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Livro{");
        sb.append("idLivro=").append(idLivro);
        sb.append(", anoPublicacao=").append(anoPublicacao);
        sb.append(", numeroExemplaresDisponiveis=").append(numeroExemplaresDisponiveis);
        sb.append(", autor=").append(autor);
        sb.append(", titulo=").append(titulo);
        sb.append('}');
        return sb.toString();
    }

}
