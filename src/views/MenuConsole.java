package views;

import controllers.BibliotecaController;
import controllers.EmprestimoController;
import models.Aluno;
import models.Professor;
import models.Livro;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private BibliotecaController bibliotecaController;
    private EmprestimoController emprestimoController;
    private RelatorioView relatorioView;
    private Scanner scanner;

    public MenuConsole(BibliotecaController bibliotecaController, EmprestimoController emprestimoController, RelatorioView relatorioView) {
        this.bibliotecaController = bibliotecaController;
        this.emprestimoController = emprestimoController;
        this.relatorioView = relatorioView;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuCadastrarLivro();
                case 2 -> menuCadastrarUsuario();
                case 3 -> menuPesquisarLivro();
                case 4 -> menuRealizarEmprestimo();
                case 5 -> menuRealizarDevolucao();
                case 6 -> menuRelatorios();
                case 0 -> System.out.println("Saindo do sistema");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("=== Gerenciamento Da Biblioteca ===");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Pesquisar Livro");
        System.out.println("4 - Realizar Empréstimo");
        System.out.println("5 - Realizar Devolução");
        System.out.println("6 - Relatórios");
        System.out.println("0 - Sair");
    }

    private void menuCadastrarLivro() {
        System.out.println("=== Cadastrar Livro ===");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Ano de publicação: ");
        int anoPublicacao = lerInt();

        System.out.print("Número de exemplares disponíveis: ");
        int exemplares = lerInt();

        try {
            Livro livro = new Livro(anoPublicacao, autor, exemplares, titulo, categoria);
            bibliotecaController.cadastrarLivro(livro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (Exception e) { 
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private void menuCadastrarUsuario() {
        System.out.println("=== Cadastrar Usuário ===");
        System.out.println("Tipo de usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.print("Escolha: ");
        int tipo = lerInt();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        try {
            if (tipo == 1) {
                System.out.print("Código de matrícula: ");
                int matricula = lerInt();

                Aluno aluno = new Aluno(email, endereco, nome, telefone, matricula);

                bibliotecaController.cadastrarUsuario(aluno);
                System.out.println("Aluno cadastrado");

            } else if (tipo == 2) {
                System.out.print("Departamento: ");
                String departamento = scanner.nextLine();
                Professor professor = new Professor(email, endereco, nome, telefone, departamento);
                bibliotecaController.cadastrarUsuario(professor);
                System.out.println("Professor cadastrado");
            } else {
                System.out.println("Tipo de usuário inválido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private void menuPesquisarLivro() {
        System.out.println("=== Pesquisar Livro ===");
        System.out.print("Digite o título, autor, categoria ou código: ");

        String termoBusca = scanner.nextLine();

        List<Livro> resultado = bibliotecaController.pesquisarLivro(termoBusca);

        if (resultado == null || resultado.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : resultado) {
                System.out.println(livro);
            }
        }
    }

    private void menuRealizarEmprestimo() {
        System.out.println("=== Realizar Empréstimo ===");
        System.out.print("ID do usuário: ");
        int idUsuario = lerInt();

        System.out.print("ID do livro: ");
        int idLivro = lerInt();
         try {
            bibliotecaController.realizarEmprestimo(idUsuario, idLivro);
            System.out.println("Empréstimo realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    private void menuRealizarDevolucao() {
        System.out.println("=== Fazer a Devolução ===");
        System.out.print("ID do empréstimo: ");
        int idEmprestimo = lerInt();

        try {
            bibliotecaController.realizarDevolucao(idEmprestimo);
            System.out.println("Devolução realizada");
        } catch (Exception e) {
            System.out.println("Erro ao realizar devolução: " + e.getMessage());
        }
    }

    private void menuRelatorios() {
        System.out.println("=== Relatórios ===");
        System.out.println("1 - Livros atualmente emprestados");
        System.out.println("2 - Devoluções em atraso");
        System.out.println("3 - Livros mais populares");
        System.out.print("Escolha: ");
        int opcao = lerInt();

        switch (opcao) {
            case 1 -> relatorioView.exibirLivrosEmprestados();
            case 2 -> relatorioView.exibirDevolucaoAtraso();
            case 3 -> relatorioView.exibirLivrosMaisPopulares();
            default -> System.out.println("Opção inválida");
        }
    }

    private int lerInt() {
        int valor = -1;
        try {
            valor = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número");
        }
        return valor;
    }
}
