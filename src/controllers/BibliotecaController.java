package controllers;

import interfaces.IGerenciadorBiblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Livro;
import models.Pessoa;

public class BibliotecaController implements IGerenciadorBiblioteca {

    private List<Livro> livros = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Pessoa> usuarios = new ArrayList<>();
    private EmprestimoController emprestimoController;

    public void setEmprestimoController(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
    }

    @Override
    public void cadastrarLivro(Livro livro) throws Exception {
        if (livro == null) throw new Exception("Livro inválido.");
        livros.add(livro);
    }

    @Override
    public List<Livro> pesquisarLivro(String termoBusca) {
        if (termoBusca == null || termoBusca.isBlank()) return new ArrayList<>();
        String termo = termoBusca.toLowerCase().trim();

        try {
        int id = Integer.parseInt(termo);
        return livros.stream()
            .filter(l -> l.getIdLivro() == id)
            .collect(Collectors.toList());
        } catch (NumberFormatException ignored){}

        return livros.stream()
                .filter(l ->
                    l.getTitulo().toLowerCase().contains(termo) ||
                    l.getAutor().toLowerCase().contains(termo)  ||
                    l.getCategoria().toLowerCase().contains(termo))
                .collect(Collectors.toList());
    }

    public Livro buscarLivroPorId(int id) {
        return livros.stream()
                .filter(l -> l.getIdLivro() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public void cadastrarUsuario(Pessoa usuario) throws Exception {
        if (usuario == null) throw new Exception("Usuário inválido.");
        usuarios.add(usuario);
    }

    public Pessoa buscarUsuarioPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Pessoa> getUsuarios() {
        return usuarios;
    }

    @Override
    public void realizarEmprestimo(int idUsuario, int idLivro) throws Exception {
        Pessoa usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) throw new Exception("Usuário não encontrado. ID: " + idUsuario);

        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) throw new Exception("Livro não encontrado. ID: " + idLivro);

        emprestimoController.realizarEmprestimo(usuario, livro);
    }                                   

    @Override
    public void realizarDevolucao(int idEmprestimo) throws Exception {
        emprestimoController.realizarDevolucao(idEmprestimo);
    }
}