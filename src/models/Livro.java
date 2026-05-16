package models;

public class Livro {

    public static int getContadorId() {
        return contadorId;
    }

    private int idLivro;
    private int anoPublicacao;
    private int numeroExemplaresDisponiveis;
    private String autor;
    private String titulo;
    private String categoria;
    private static int contadorId = 1;
    

    public Livro(int anoPublicacao, String autor, int numeroExemplaresDisponiveis, String titulo,String categoria) {
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.idLivro = contadorId++;
        this.numeroExemplaresDisponiveis = numeroExemplaresDisponiveis;
        this.titulo = titulo;
        this.categoria= categoria;
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
    
     public String getCategoria() {
        return categoria;
     }

    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro + ", anoPublicacao=" + anoPublicacao + ", numeroExemplaresDisponiveis="+ numeroExemplaresDisponiveis + ", autor=" + autor + ", titulo=" + titulo + ", categoria=" + categoria + "]";
    }

}
