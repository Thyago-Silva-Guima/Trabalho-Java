package views;

import controllers.EmprestimoController;
import interfaces.IGeradorRelatorio;

public class RelatorioView implements IGeradorRelatorio {

    private EmprestimoController emprestimoController;

    public RelatorioView(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
    }

    @Override
    public void exibirLivrosEmprestados() {
        System.out.println("=== Livros Emprestados Atualmente ===");
        emprestimoController.listarEmprestimosAtivos();
    }

    @Override
    public void exibirDevolucaoAtraso() {
        System.out.println("=== Devoluções em Atraso ===");
        emprestimoController.listarEmprestimosAtrasados();
    }

    @Override
    public void exibirLivrosMaisPopulares() {
        System.out.println("=== Livros Mais Populares ===");
        emprestimoController.listarLivrosMaisPopulares();
    }
}