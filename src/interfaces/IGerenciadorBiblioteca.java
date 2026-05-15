package interfaces;
import models.Livro;
import models.Pessoa;
import java.util.List;

public interface IGerenciadorBiblioteca {

    void cadastrarLivro(Livro livro) throws Exception;
    void cadastrarUsuario(Pessoa usuario) throws Exception;

    List<Livro> pesquisarLivro(String termoBusca);

    void realizarEmprestimo(int idUsuario,int idLivro) throws Exception;

    void realizarDevolucao(int idEmprestimo) throws Exception;
    
}
