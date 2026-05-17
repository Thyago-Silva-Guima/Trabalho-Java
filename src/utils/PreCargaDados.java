package utils;

import controllers.BibliotecaController;
import controllers.EmprestimoController;
import models.Aluno;
import models.Livro;
import models.Professor;

public class PreCargaDados {

    private BibliotecaController bibliotecaController;
    private EmprestimoController emprestimoController;

    public PreCargaDados(BibliotecaController bibliotecaController, EmprestimoController emprestimoController) {
        this.bibliotecaController = bibliotecaController;
        this.emprestimoController = emprestimoController;
    }

    public void carregar() {
        System.out.println("Carregando dados iniciais...");
        try {
            carregarLivros();
            carregarUsuarios();
            carregarEmprestimosEDevolucoes();
            System.out.println("Esses são seus dados");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void carregarLivros() throws Exception {
        bibliotecaController.cadastrarLivro(new Livro(2001, "J.R.R. Tolkien", 3, "O Senhor dos Anéis", "Fantasia"));
        bibliotecaController.cadastrarLivro(new Livro(1984, "George Orwell", 2, "1984", "Ficção Científica"));
        bibliotecaController.cadastrarLivro(new Livro(1997, "J.K Rowling", 4, "Harry Poter", "Fantasia"));
        bibliotecaController.cadastrarLivro(new Livro(1943, "Antoine de Saint-Exupéry", 2, "O Pequeno Principe", "Filosofia"));
        bibliotecaController.cadastrarLivro(new Livro(1605, "Miguel de Cervantes", 3, "Dom Quixote", "Aventura"));
    }

    private void carregarUsuarios() throws Exception {
        bibliotecaController.cadastrarUsuario(new Aluno("Lorenzo@email.com", "Rua das Flores, 10", "Lorenzo Vanelli", "44991187701", 1001));
        bibliotecaController.cadastrarUsuario(new Aluno("Luan@email.com", "Av. Brasil, 200", "Luan Neuwirth", "44991910322", 1002));
        bibliotecaController.cadastrarUsuario(new Professor("Thyago@email.com", "Rua do Saber, 50", "Thyago Silva", "44991112073", "Ciências da Computação"));
        bibliotecaController.cadastrarUsuario(new Professor("Murilo@email.com", "Av. Central, 300", "Murilo Opis", "449921102304", "Letras"));
    }

    private void carregarEmprestimosEDevolucoes() throws Exception {
        bibliotecaController.realizarEmprestimo(1, 2);

        bibliotecaController.realizarEmprestimo(3, 4);

        bibliotecaController.realizarEmprestimo(2, 1);
        bibliotecaController.realizarDevolucao(3);
    }
}